package com.darcess.instacaster.ui;

import android.content.Intent;
import android.os.Bundle;

import com.darcess.instacaster.R;
import com.darcess.instacaster.di.DaggerMainComponent;
import com.darcess.instacaster.di.MainModule;
import com.darcess.instacaster.Base.BaseActivity;
import com.darcess.instacaster.mvp.Presenter.MainPresenter;
import com.darcess.instacaster.mvp.View.MainView;

import javax.inject.Inject;

import butterknife.OnClick;


public class MainActivity extends BaseActivity  implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    protected MainPresenter mPresenter;

    @OnClick(R.id.reloginB)
    public void onClick() {
        mPresenter.reloginInstagram();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent){
        super.onViewReady(savedInstanceState, intent);

        mPresenter.initInstagram(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void resolveDependencies(){
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

}
