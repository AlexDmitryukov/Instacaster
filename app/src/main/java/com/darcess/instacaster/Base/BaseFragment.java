package com.darcess.instacaster.Base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Alexander Dmitryukov on 7/14/2017.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;

    protected abstract int getResourceId();

    protected abstract void initView(View view);

    protected ProgressDialog progressDialog;
    protected Dialog alertDialog;
    protected IBaseFragmentListener mBaseFragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getResourceId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IBaseFragmentListener) {
            mBaseFragmentListener = (IBaseFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    protected void replaceFragment(Fragment fragment, boolean addToBackStack, int frameId){
        checkNotNull(fragment);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(frameId, fragment);
        if(addToBackStack && transaction.isAddToBackStackAllowed()) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void showDialog(String message){
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);
        }
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void hide() {
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}