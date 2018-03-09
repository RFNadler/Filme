package com.example.nadler.filmes.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nadler.filmes.R;
import com.example.nadler.filmes.repository.results;
import com.example.nadler.filmes.setting.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Nadler on 08/03/2018.
 */

public class CatalogGridViewAdapter extends ArrayAdapter<results> {
    private OnItemClickListener.OnItemClickCallback onItemClickCallback;
    private Context cContext;
    private int layoutResourceId;
    private ArrayList<results> alGenreFilm;
    private String strUrlImage;

    public CatalogGridViewAdapter(Context cCONTEXT, int pLAYOUTID, ArrayList<results> pGENREFILM,
                                  String pURLIMAGE,
                                  OnItemClickListener.OnItemClickCallback onItemClickCallback) {
        super(cCONTEXT, pLAYOUTID, pGENREFILM);
        this.layoutResourceId = pLAYOUTID;
        this.cContext = cCONTEXT;
        this.alGenreFilm = pGENREFILM;
        this.strUrlImage = pURLIMAGE;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) cContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.llMain = (LinearLayout) row.findViewById(R.id.GridItemFilmLlMain);
            holder.ivPost = (ImageView) row.findViewById(R.id.GridItemFilmIvPoster);
            holder.tvTitle = (TextView) row.findViewById(R.id.GridItemFilmTvTitle);
            holder.tvRate = (TextView) row.findViewById(R.id.GridItemFilmTvRate);
            holder.tvVote = (TextView) row.findViewById(R.id.GridItemFilmTvVote);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        results gfItem = alGenreFilm.get(position);
        holder.tvTitle.setText(gfItem.title);
        Picasso.with(cContext).load(strUrlImage + gfItem.poster_path).fit().into(holder.ivPost);
        holder.tvRate.setText(gfItem.vote_average + "");
        holder.tvVote.setText(gfItem.vote_count + "");
        holder.llMain.setOnClickListener(new OnItemClickListener(gfItem.id, onItemClickCallback));

        return row;
    }

    static class ViewHolder {
        LinearLayout llMain;
        TextView tvTitle;
        ImageView ivPost;
        TextView tvRate;
        TextView tvVote;
    }

}