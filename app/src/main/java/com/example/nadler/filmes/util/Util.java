package com.example.nadler.filmes.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;

import com.example.nadler.filmes.R;

/**
 * Created by Nadler on 08/03/2018.
 */

public class Util {

    public static Dialog openProgress(Context cCONTEXT){
        AlertDialog.Builder adbProgress = new AlertDialog.Builder(cCONTEXT);
        adbProgress.setView(R.layout.dialog_progress);
        Dialog dProgressTemp = adbProgress.create();
        dProgressTemp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dProgressTemp.setCancelable(false);
        return dProgressTemp;
    }
}
