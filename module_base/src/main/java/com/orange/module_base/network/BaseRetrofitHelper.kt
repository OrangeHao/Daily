package com.orange.module_base.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * created by czh on 2018/5/9
 */
abstract class BaseRetrofitHelper{

    val retrofit: Retrofit
    val okHttpClient: OkHttpClient

    init {
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(logInterceptor).build()
        //        mOkHttpClient = new OkHttpClient.Builder()
        //                .addNetworkInterceptor(
        //                        new Interceptor() {
        //                            @Override
        //                            public Response intercept(Chain chain) throws IOException {
        //                                Request request = chain.request();
        //
        //                                //在这里获取到request后就可以做任何事情了
        //                                Response response = chain.proceed(request);
        //                                Log.d(TAG, "intercept: "+request.url().toString());
        //                                return response;
        //                            }
        //                        }
        //                ).build();

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(getConverFactory())
                .addCallAdapterFactory(getCallAdapterFactory())
                .baseUrl(getBaseUrl())
                .build()
    }


    abstract fun getBaseUrl():String

    open fun getConverFactory(): Converter.Factory{
        return GsonConverterFactory.create()
    }

    open fun getCallAdapterFactory(): CallAdapter.Factory{
        return RxJava2CallAdapterFactory.create()
    }
}
