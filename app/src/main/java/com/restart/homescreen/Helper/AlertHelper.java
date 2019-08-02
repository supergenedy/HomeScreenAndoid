package com.restart.homescreen.Helper;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.restart.homescreen.R;

/**
 * Created by Ahmed El Genedy on 02-Aug-19.
 * Copyright (C) 2019 SuperGenedy. supergenedy@gmail.com All Rights Reserved.
 */
public class AlertHelper {

    public AlertDialog show(Context context, String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        if (!title.isEmpty()) alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertDialog.create();
        dialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.shape_dialog_corner));
        return dialog;
    }
}