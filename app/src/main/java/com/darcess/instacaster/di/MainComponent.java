package com.darcess.instacaster.di;

import com.darcess.instacaster.ui.MainActivity;

import dagger.Component;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

@PerActivity
@Component(modules = MainModule.class, dependencies = ApplicationComponent.class)
public interface MainComponent {


    void inject(MainActivity activity);
}
