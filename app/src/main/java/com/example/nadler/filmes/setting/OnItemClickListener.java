package com.example.nadler.filmes.setting;

import android.view.View;

/**
 * Created by Nadler on 23/06/2016.
 */
public class OnItemClickListener implements View.OnClickListener {
    private long lngPosition;
    private OnItemClickCallback onItemClickCallback;

    public OnItemClickListener(long position, OnItemClickCallback onItemClickCallback) {
        this.lngPosition = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, lngPosition);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, long pPOSITION);
    }
}
