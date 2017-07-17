package com.darcess.instacaster.di;

import com.darcess.instacaster.api.InstagramApiService;
import com.darcess.instacaster.mvp.View.MainView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

@Module
public class MainModule {

    private MainView mView;

    public MainModule(MainView mView) {
        this.mView = mView;
    }

    @PerActivity
    @Provides
    MainView provideView() {
        return mView;
    }

    @PerActivity
    @Provides
    InstagramApiService provideService(Retrofit retrofit) {
        return retrofit.create(InstagramApiService.class);
    }
}
