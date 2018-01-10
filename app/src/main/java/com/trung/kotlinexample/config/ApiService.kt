package com.trung.kotlinexample.config

import android.content.Context
import com.google.gson.GsonBuilder
import com.trung.kotlinexample.BuildConfig
import com.trung.kotlinexample.model.jobresult.ListJobResult
import com.trung.kotlinexample.model.loginResult.LoginResult
import com.trung.kotlinexample.network.RxErrorHandlingCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable
import java.util.concurrent.TimeUnit


interface ApiService {
    @FormUrlEncoded
    @POST("ecom-services/api/authenticate")
    fun postLogin(@Field("username") username: String, @Field("password") password: String)
            : Observable<Response<LoginResult>>

    @GET("ecom-services/api/mobile/tasks/user/{id}/upcoming")
    fun getListJob(
            @Header("Cookie") cookie: String,
            @Path("id") id: String): Observable<ListJobResult>

    object Factory {

        fun create(context: Context): ApiService {
            // Create Retrofit Adapter
            val gson = GsonBuilder().serializeNulls().create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.ENDPOINT)
                    .client(getOkHttp(context))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }

        private fun getOkHttp(context: Context): OkHttpClient {

            // Config Log
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            // Create Http Client
            val httpClient = OkHttpClient.Builder()

            with(httpClient) {
                addInterceptor { chain ->
                    val builder = chain.request().newBuilder()
//                    if (UserConfig.getInstance(context).isLogined()) {
//                        builder.addHeader("Authorization", "Bearer "
//                                + UserConfig.getInstance(context).token)
//                    }
                    chain.proceed(builder.build())
                            .newBuilder()
                            .build()
                }
                connectTimeout(10, TimeUnit.SECONDS)
                readTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    addInterceptor(logging)
                }
                // Config Http Cache
                val cacheSize = 10 * 1024 * 1024 // 10 MiB
                val cache = Cache(context.cacheDir, cacheSize.toLong())
                cache(cache)
            }
            return httpClient.build()
        }
    }
}
