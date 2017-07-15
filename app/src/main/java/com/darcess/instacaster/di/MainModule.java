package com.darcess.instacaster.di;

import com.darcess.instacaster.mvp.View.MainView;

import dagger.Module;
import dagger.Provides;

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
}
