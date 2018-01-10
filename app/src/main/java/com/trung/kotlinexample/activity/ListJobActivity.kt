package com.trung.kotlinexample.activity

import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.trung.kotlinexample.MyApplication
import com.trung.kotlinexample.R
import com.trung.kotlinexample.adapter.ListJobAdapter
import com.trung.kotlinexample.config.ApiService
import com.trung.kotlinexample.config.AppConfig
import com.trung.kotlinexample.databinding.ActivityListJobBinding
import com.trung.kotlinexample.model.jobresult.JobItem
import com.trung.kotlinexample.model.jobresult.ListJobResult
import com.trung.kotlinexample.utils.AppUtils
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListJobActivity : AppCompatActivity() {
    private lateinit var listjobBinding: ActivityListJobBinding
    private lateinit var apiService: ApiService
    private var mSubscription: Subscription? = null
    private var mListJobResult: ListJobResult? = null
    private var id: String? = ""
    private var cookie: String? = ""
    private lateinit var adapter: ListJobAdapter
    private var progressBar: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listjobBinding = DataBindingUtil.setContentView(this, R.layout.activity_list_job)
        id = intent.getStringExtra("id")
        cookie = intent.getStringExtra("cookie")
        bindData()
    }

    private fun bindData() {
        apiService = MyApplication.with(this).getApiService()
        adapter = ListJobAdapter(this, ArrayList())
        listjobBinding.rvList.adapter = adapter
        callData()

    }

    private fun callData() {
        progressBar = ProgressDialog.show(this, "", getString(R.string.loading), false)
        if (mSubscription != null && !mSubscription!!.isUnsubscribed)
            mSubscription!!.unsubscribe()
        mSubscription = apiService.getListJob(cookie = cookie!!, id = id!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Subscriber<ListJobResult>() {
                    override fun onCompleted() {
                        progressBar?.let {
                            if (it.isShowing) {
                                it.dismiss()
                            }
                        }
                        if (mListJobResult != null && "success".contentEquals(mListJobResult?.type!!)) {
                            mListJobResult?.data?.let {
                                if (it.isNotEmpty()) {
                                    val item0 = it[0].copy(type_item = JobItem.TYPE_HEADER)
                                    adapter.add(item0)
                                    adapter.add(it[0])
                                }
                                if (it.size > 1) {
                                    for (i in 1 until it.size) {
                                        if (!AppUtils.convertLongToDate(it[i].requestDate)
                                                .equals(AppUtils.convertLongToDate(it[i - 1].requestDate))) {
                                            val itemI = it[i].copy(type_item = JobItem.TYPE_HEADER)
                                            adapter.add(itemI)
                                        }
                                        adapter.add(it[i])
                                    }
                                }

                            }

                        }
                    }

                    override fun onError(e: Throwable) {
                        progressBar?.let {
                            if (it.isShowing) {
                                it.dismiss()
                            }
                        }
                        try {
                            if (AppUtils.getMessageErrorApi(e).isNullOrEmpty()) {
                                Toast.makeText(this@ListJobActivity, getString(R.string.error_network),
                                        Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@ListJobActivity, AppUtils.getMessageErrorApi(e),
                                        Toast.LENGTH_SHORT).show()
                            }

                        } catch (ex: Throwable) {
                            Toast.makeText(this@ListJobActivity, getString(R.string.error_network),
                                    Toast.LENGTH_SHORT).show()
                        }
                        AppConfig.getInstance(this@ListJobActivity).clearData()
                        val intent = Intent(this@ListJobActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    override fun onNext(listJobResult: ListJobResult?) {
                        if (listJobResult != null) {
                            mListJobResult = listJobResult
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
