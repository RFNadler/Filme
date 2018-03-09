package com.example.nadler.filmes.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadler.filmes.R;
import com.example.nadler.filmes.adapter.CatalogGridViewAdapter;
import com.example.nadler.filmes.repository.MOVIE;
import com.example.nadler.filmes.repository.genredetail;
import com.example.nadler.filmes.repository.results;
import com.example.nadler.filmes.setting.OnItemClickListener;

import java.util.ArrayList;

/**
 * Created by Nadler on 08/03/2018.
 */

public class Main extends AppCompatActivity {
    Activity acMain;
    MOVIE filme;
    LinearLayout llCatalog;
    private CatalogGridViewAdapter cgvCatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        acMain = this;

        llCatalog = (LinearLayout) findViewById(R.id.MainLlCatalog);

        filme = (MOVIE) getIntent().getSerializableExtra("objFILME");
        if(filme == null)
            Toast.makeText(this, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();

        for(genredetail genre : filme.genres.genres){
            AddCatalog(filme.images.secure_base_url + "w185/",
                    genre.name, genre.genremovie.results);
        }

    }

    private void AddCatalog(String pURLIMAGE, String pGENRE, ArrayList<results> pFILM){

        int intWidth = 105 ;

        DisplayMetrics dmScreen = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dmScreen);
        float density = dmScreen.density;

        int intTotWidth = (int) (intWidth * pFILM.size() * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                intTotWidth, LinearLayout.LayoutParams.MATCH_PARENT);

        LayoutInflater liInflate = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View vwImagem = liInflate.inflate(R.layout.include_catalog,null);
        TextView tvGenre = (TextView) vwImagem.findViewById(R.id.IncludeCatalogTvGenre);
        tvGenre.setText(pGENRE.toUpperCase());

        GridView gvCatalog = (GridView) vwImagem.findViewById(R.id.IncludeCatalogGvMain);

        gvCatalog.setLayoutParams(params);
        gvCatalog.setColumnWidth(200);
        gvCatalog.setHorizontalSpacing(2);
        gvCatalog.setStretchMode(GridView.STRETCH_SPACING);
        gvCatalog.setNumColumns(pFILM.size());

        cgvCatalog = new CatalogGridViewAdapter(this, R.layout.grid_item_film, pFILM,
                pURLIMAGE,onItemClickCallback);
        gvCatalog.setAdapter(cgvCatalog);

        llCatalog.addView(vwImagem);
    }

    private OnItemClickListener.OnItemClickCallback onItemClickCallback = new OnItemClickListener.OnItemClickCallback() {
        @Override
        public void onItemClicked(View view, long position) {
            Intent minhaAcao = new Intent(acMain,Movie.class);
            minhaAcao.putExtra("urlImage",filme.images.secure_base_url);
            minhaAcao.putExtra("id",position);
            startActivity(minhaAcao);
        }
    };
}
