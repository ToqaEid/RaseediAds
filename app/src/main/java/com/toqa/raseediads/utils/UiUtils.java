package com.toqa.raseediads.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

public class UiUtils {

    /**
     * Create AlertDialog with one or two buttons according to params
     * if @param btn2Listener != null then there will be 2 buttons
     *
     * @param title         alertDialog error title
     * @param msg           alertDialog error message
     * @param btn2Str       name of neutral button 2
     * @param okBtnListener ClickListener of button 1
     * @param btn2Listener  ClickListener of button 2
     */
    public static AlertDialog.Builder initAlertDialog(Context context, String title, String msg, int btn2Str, DialogInterface.OnClickListener okBtnListener, DialogInterface.OnClickListener btn2Listener) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, okBtnListener)
                .setIcon(android.R.drawable.ic_dialog_alert);

        if (btn2Listener != null) {
            builder.setNeutralButton(btn2Str, btn2Listener);

        }

        return builder;
    }
}
