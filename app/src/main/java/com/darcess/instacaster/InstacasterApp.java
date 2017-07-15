package com.darcess.instacaster;

import android.app.Application;

import com.darcess.instacaster.di.ApplicationComponent;
import com.darcess.instacaster.di.ApplicationModule;
import com.darcess.instacaster.di.DaggerApplicationComponent;

import static com.darcess.instacaster.util.Global.INSTAGRAM_API_BASE;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

public class InstacasterApp extends Application {

    private ApplicationComponent mComponent;
    private static final String TAG = Application.class.getCanonicalName();


    @Override
    public void onCreate() {
        super.onCreate();
        initializeApplicationComponent();

    }

    //Note
    //DaggerApplicationComponent is generated automatically by dagger
    //Please clean project > build before Running
    private void initializeApplicationComponent() {
        mComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(INSTAGRAM_API_BASE, this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mComponent;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
