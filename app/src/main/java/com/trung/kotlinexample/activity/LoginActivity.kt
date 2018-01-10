package com.trung.kotlinexample.activity

import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jackandphantom.blurimage.BlurImage
import com.trung.kotlinexample.MyApplication
import com.trung.kotlinexample.config.ApiService
import com.trung.kotlinexample.config.AppConfig
import com.trung.kotlinexample.model.loginResult.LoginResult
import com.trung.kotlinexample.utils.AppUtils
import com.trung.kotlinexample.viewmodel.LoginViewModel
import retrofit2.Response
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import com.trung.kotlinexample.R
import com.trung.kotlinexample.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var apiService: ApiService
    private var mSubscription: Subscription? = null
    private var mLoginResult: LoginResult? = null
    private var cookie: String? = ""
    private var progressBar: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = LoginViewModel()
        loginBinding.loginViewModel = loginViewModel
        if (AppConfig.getInstance(this@LoginActivity).session.isEmpty()) {
            BlurImage.with(this@LoginActivity).load(R.drawable.bg_login).intensity(20F)
                    .Async(true).into(loginBinding.imgLogin)
            loginBinding.btnLogin.setOnClickListener {
                val username = loginBinding.edUserName.text.toString()
                val password = loginBinding.edPassword.text.toString()
                if (username.isEmpty()) {
                    Toast.makeText(this@LoginActivity, getString(R.string.pleaseInputUserName),
                            Toast.LENGTH_SHORT).show()
                } else if (password.isEmpty()) {
                    Toast.makeText(this@LoginActivity, getString(R.string.pleaseInputPassword),
                            Toast.LENGTH_SHORT).show()
                } else {
                    login(username, password)
                }

            }
        } else {
            val intent = Intent(this@LoginActivity, ListJobActivity::class.java)
            with(intent) {
                putExtra("id", AppConfig.getInstance(this@LoginActivity).userId)
                putExtra("cookie", AppConfig.getInstance(this@LoginActivity).session)
                startActivity(this@with)
                finish()
            }
        }

    }

    private fun login(username: String, password: String) {
        loginViewModel.isEnableButton.set(false)
        progressBar = ProgressDialog.show(this, "", getString(R.string.loading), false)
        apiService = MyApplication.with(this).getApiService()
        if (mSubscription != null && !mSubscription!!.isUnsubscribed)
            mSubscription!!.unsubscribe()
        mSubscription = apiService.postLogin(username = username, password = password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<Response<LoginResult>>() {
                    override fun onCompleted() {
                        progressBar?.let {
                            if (it.isShowing) {
                                it.dismiss()
                            }
                        }
                        loginBinding.btnLogin.isEnabled = true
                        if (mLoginResult != null && "success".contentEquals(mLoginResult?.type!!)) {
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT)
                                    .show()
                            cookie?.let {
                                AppConfig.getInstance(this@LoginActivity).session = it
                            }
                            mLoginResult?.data?.userInfo?.id?.let {
                                AppConfig.getInstance(this@LoginActivity).userId = it
                            }
                            val intent = Intent(this@LoginActivity, ListJobActivity::class.java)
                            with(intent) {
                                putExtra("id", mLoginResult?.data?.userInfo?.id)
                                putExtra("cookie", cookie)
                                startActivity(this@with)
                                finish()
                            }

                        }
                    }

                    override fun onError(e: Throwable) {
                        progressBar?.let {
                            if (it.isShowing) {
                                it.dismiss()
                            }
                        }
                        loginViewModel.isEnableButton.set(true)
                        try {
                            if (AppUtils.getMessageErrorApi(e).isNullOrEmpty()) {
                                Toast.makeText(this@LoginActivity, getString(R.string.error_network),
                                        Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@LoginActivity, AppUtils.getMessageErrorApi(e),
                                        Toast.LENGTH_SHORT).show()
                            }

                        } catch (ex: Throwable) {
                            Toast.makeText(this@LoginActivity, getString(R.string.error_network),
                                    Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onNext(loginResult: Response<LoginResult>?) {
                        if (loginResult != null) {
                            mLoginResult = loginResult.body()
                            val header = loginResult.headers().toMultimap()
                            val listCookie: List<String> = header.getValue("Set-Cookie")
                            for (ck: String in listCookie) {
                                if (ck.contains("JSESSIONID")) {
                                    cookie = ck
                                    break
                                }
                            }
                        }
                    }
                })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mSubscription != null && !mSubscription!!.isUnsubscribed) mSubscription!!.unsubscribe()
        mSubscription = null
    }
}
