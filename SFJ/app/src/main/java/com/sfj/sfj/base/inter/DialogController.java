package com.sfj.sfj.base.inter;


import com.sfj.sfj.widget.WaitDialog;

/**
 * Created by wangyu on 2018/1/24.
 */

public interface DialogController {

    void hideWaitDialog();

    WaitDialog showWaitDialog();

    WaitDialog showWaitDialog(int resid);

    WaitDialog showWaitDialog(String text);
}
