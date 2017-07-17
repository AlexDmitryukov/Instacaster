package com.darcess.instacaster.Base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.darcess.instacaster.InstacasterApp;
import com.darcess.instacaster.R;
import com.darcess.instacaster.di.ApplicationComponent;

import static com.google.common.base.Preconditions.checkNotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private FragmentManager mFm;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getSupportFragmentManager();
        setContentView(getContentView());
        unbinder = ButterKnife.bind(this);
        onViewReady(savedInstanceState, getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy(){
        unbinder.unbind();
        super.onDestroy();
    }

    protected abstract int getContentView();

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        resolveDependencies();
    }

    protected void resolveDependencies() {
    }

    protected void replaceFragment(@NonNull Fragment fragment, boolean addToBackStack){
        checkNotNull(fragment);
        FragmentTransaction transaction = mFm.beginTransaction();
        transaction.replace(R.id.main_content, fragment);
        if(addToBackStack && transaction.isAddToBackStackAllowed()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((InstacasterApp) getApplication()).getApplicationComponent();
    }

    protected void showProgressDialog(String message){
        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCancelable(true);
        }
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    protected void hideProgressDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }


}
