package com.sfj.sfj.base;

import android.content.Context;

import com.sfj.sfj.R;
import com.sfj.sfj.widget.WaitDialog;


/**
 * Created by wangyu on 2018/1/24.
 */

class DialogHelper {

    public static WaitDialog getWaitDialog(Context activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.dialog_waiting);
            dialog.setMessage(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }
}
