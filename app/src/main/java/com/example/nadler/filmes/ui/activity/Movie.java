package com.example.nadler.filmes.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nadler.filmes.R;
import com.example.nadler.filmes.repository.moviedetail;
import com.example.nadler.filmes.service.APIService;
import com.example.nadler.filmes.util.Util;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nadler on 08/03/2018.
 */

public class Movie extends AppCompatActivity {
    Activity acMain;
    ScrollView svMain;
    ImageView ivBackdrop;
    ImageView ivPoster;
    TextView tvTitle;
    TextView tvYearTime;
    TextView tvVoteAverage;
    TextView tvOverview;
    moviedetail movie;
    String strUrlImage;

    Dialog dalProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);
        acMain = this;

        svMain = (ScrollView) findViewById(R.id.MovieSvMain);
        ivBackdrop = (ImageView) findViewById(R.id.MovieIvBackdrop);
        ivPoster = (ImageView) findViewById(R.id.MovieIvPoster);
        tvTitle = (TextView) findViewById(R.id.MovieTvTitle);
        tvYearTime = (TextView) findViewById(R.id.MovieTvYearTime);
        tvVoteAverage = (TextView) findViewById(R.id.MovieTvVoteAverage);
        tvOverview = (TextView) findViewById(R.id.MovieTvOverview);

        strUrlImage = getIntent().getStringExtra("urlImage") + "";
        getFilm(getIntent().getLongExtra("id",0));
    }

    private void getFilm(long pID){
        dalProgress = Util.openProgress(acMain);
        dalProgress.show();

        try{
            APIService.getMovie getMovie = APIService.getMovie.retrofit.create(APIService.getMovie.class);
            final Call<moviedetail> call =  getMovie.getMovie(pID,
                    getString(R.string.apiKey),
                    getString(R.string.languageDefault));
            call.enqueue(new Callback<moviedetail>() {
                @Override
                public void onResponse(Call<moviedetail> call, Response<moviedetail> response) {
                    if(response.body() == null || response.code() != 200){
                        dalProgress.dismiss();
                        Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
                    }else{
                        movie = response.body();
                        Picasso.with(acMain).load(strUrlImage + "w500" + movie.backdrop_path).fit().into(ivBackdrop);
                        Picasso.with(acMain).load(strUrlImage + "w185" + movie.poster_path).fit().into(ivPoster);
                        tvTitle.setText(movie.title);
                        tvYearTime.setText(movie.release_date.substring(0,4) + ", " + movie.runtime + " minutos");
                        tvVoteAverage.setText(movie.vote_average + "");
                        tvOverview.setText(movie.overview);
                        svMain.setVisibility(View.VISIBLE);
                        dalProgress.dismiss();
                    }
                }
                @Override
                public void onFailure(Call<moviedetail> call, Throwable t) {
                    dalProgress.dismiss();
                    Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
                }
            });

        }catch (Exception ex){
            ex.printStackTrace();
            dalProgress.dismiss();
            Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
        }
    }

}
