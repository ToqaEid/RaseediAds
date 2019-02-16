package com.toqa.raseediads.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

public class UiUtils {

    public static AlertDialog.Builder initAlertDialog(Context context, String title, String msg, int btn2Str, DialogInterface.OnClickListener positiveBtnListener, DialogInterface.OnClickListener btn2Listener) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, positiveBtnListener)
                .setIcon(android.R.drawable.ic_dialog_alert);

        if (btn2Listener != null) {
            builder.setNeutralButton(btn2Str, btn2Listener);

        }

        return builder;
    }
}
