package com.darcess.instacaster.di;

import android.content.Context;

import com.darcess.instacaster.InstacasterApp;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Retrofit exposeRetrofit();
    Context exposeContext();

    void inject(InstacasterApp application);
}
