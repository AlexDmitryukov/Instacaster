package com.darcess.instacaster.di;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

@Module
public class ApplicationModule {

    private Context mContext;
    private String mBaseUrl;

    public ApplicationModule(String mbaseUrl, Context mContext) {
        this.mBaseUrl = mbaseUrl;
        this.mContext = mContext;
    }


    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactoy() {
        GsonConverterFactory factory = GsonConverterFactory.create();
        return factory;
    }

    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client, GsonConverterFactory converterFactory, RxJava2CallAdapterFactory adapterFactory){
        return new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }

}
