package net.wepla.library_common;

import android.app.Activity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;



/**
 * Created by bek on 2/1/16.
 */
public abstract class ParentTabActivity extends AppCompatActivity implements ParentViewInterface {

    public Application pandaApplication;
    public InputMethodManager imm;
    private CircularProgress progressBar;
    protected Handler mHandler;
    private ProgressDialog progressBarDialog;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressBar = new CircularProgress(this);
        progressBarDialog = new ProgressDialog(ParentTabActivity.this);
        progressBarDialog.setCancelable(false);
        progressBar.setCancelable(false);
        progressBar.setCanceledOnTouchOutside(false);
        if (mHandler == null) mHandler = new Handler();
        pandaApplication = this.getApplication();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        this.context = this;
    }


    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressBar != null) {
            progressBar.dismiss();
            progressBar = null;
        }
    }

    public void setProgressable(boolean enable) {
        if (enable) {
            if (progressBar != null) progressBar.show();
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (progressBar != null) progressBar.dismiss();
                }
            }, 500);
        }
    }


    public void setProgressableWithDialog(String string, boolean enable) {
        if (enable) {
            if (progressBarDialog != null) {
                if (!progressBarDialog.isShowing() && !isFinishing())
                    progressBarDialog.show();
                progressBarDialog.setMessage(string);
            }
        } else {
            if (string != null && string.length() > 0)
                progressBarDialog.setMessage(string);

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (progressBarDialog != null) progressBarDialog.dismiss();
                }
            }, 2000);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    public boolean isDialogProgressing() {
        return (progressBarDialog != null) && (progressBarDialog.isShowing());
    }

    public boolean isProgressing() {
        return (progressBar != null) && (progressBar.isShowing());
    }

    public CircularProgress getProgressBar() {
        return progressBar;
    }

    public ProgressDialog getProgressBarDialog() {
        return progressBarDialog;
    }


    protected Handler getDefaultHandler() {
        if (mHandler == null) mHandler = new Handler();
        return mHandler;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void setProgressIndicator(boolean active) {
        setProgressable(active);
    }


}
