package com.sfj.sfj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sfj.sfj.R;
import com.sfj.sfj.base.inter.DialogController;
import com.sfj.sfj.widget.WaitDialog;

import me.yokeyword.fragmentation.app.SupportActivity;

/**
 * Created by wangyu on 2018/1/24.
 */

public class BaseActivity extends SupportActivity implements DialogController {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private WaitDialog _waitDialog;

    @Override
    public WaitDialog showWaitDialog() {
        return showWaitDialog(R.string.loading);
    }

    @Override
    public WaitDialog showWaitDialog(int resid) {
        return showWaitDialog(getString(resid));
    }

    @Override
    public WaitDialog showWaitDialog(String message) {

        if (_waitDialog == null) {
            _waitDialog = DialogHelper.getWaitDialog(this, message);
        }
        if (_waitDialog != null) {
            _waitDialog.setMessage(message);
            _waitDialog.show();
        }
        return _waitDialog;
    }

    @Override
    public void hideWaitDialog() {
        if ( _waitDialog != null&&_waitDialog.isShowing()) {
            try {
                _waitDialog.dismiss();
                _waitDialog = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideWaitDialog();
    }
}
