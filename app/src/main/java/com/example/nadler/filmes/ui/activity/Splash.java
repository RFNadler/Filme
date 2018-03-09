package com.example.nadler.filmes.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.nadler.filmes.R;
import com.example.nadler.filmes.repository.MOVIE;
import com.example.nadler.filmes.repository.genremovie;
import com.example.nadler.filmes.repository.genres;
import com.example.nadler.filmes.service.APIService;
import com.example.nadler.filmes.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Splash extends AppCompatActivity {
    Activity acMain;
    Dialog dalProgress;
    MOVIE movie;
    int intIdxGenre = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        acMain = this;
        movie = new MOVIE();
        getConfig();
    }

    private void getConfig(){
        dalProgress = Util.openProgress(acMain);
        dalProgress.show();

        try{
            APIService.getConfig getconfig = APIService.getConfig.retrofit.create(APIService.getConfig.class);
            final Call<MOVIE> call =  getconfig.getConfig(getString(R.string.apiKey));
            call.enqueue(new Callback<MOVIE>() {
                @Override
                public void onResponse(Call<MOVIE> call, Response<MOVIE> response) {
                    if(response.body() == null || response.code() != 200){
                        dalProgress.dismiss();
                        Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
                    }else{
                        movie = response.body();
                        getGenre();
                    }
                }
                @Override
                public void onFailure(Call<MOVIE> call, Throwable t) {
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

    private void getGenre(){
        try{
            APIService.getGenre getgenre = APIService.getGenre.retrofit.create(APIService.getGenre.class);
            final Call<genres> call =  getgenre.getGenre(getString(R.string.apiKey),getString(R.string.languageDefault));
            call.enqueue(new Callback<genres>() {
                @Override
                public void onResponse(Call<genres> call, Response<genres> response) {
                    if(response.body() == null || response.code() != 200){
                        dalProgress.dismiss();
                        Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
                    }else{
                        movie.genres = response.body();
                        getCatalogFilm();
                    }
                }
                @Override
                public void onFailure(Call<genres> call, Throwable t) {
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

    private void getFilm(){
        try{
            APIService.getMovieByGenre getFilmByGenre = APIService.getMovieByGenre.retrofit.create(APIService.getMovieByGenre.class);
            final Call<genremovie> call =  getFilmByGenre.getMovieByGenre(movie.genres.genres.get(intIdxGenre).id,
                    getString(R.string.apiKey),
                    getString(R.string.languageDefault),
                    "false","popularity.desc");
            call.enqueue(new Callback<genremovie>() {
                @Override
                public void onResponse(Call<genremovie> call, Response<genremovie> response) {
                    if(response.body() == null || response.code() != 200){
                        dalProgress.dismiss();
                        Toast.makeText(acMain, getString(R.string.txtErroServidor), Toast.LENGTH_LONG).show();
                    }else{
                        movie.genres.genres.get(intIdxGenre).genremovie = response.body();
                        intIdxGenre++;
                        getCatalogFilm();
                    }
                }
                @Override
                public void onFailure(Call<genremovie> call, Throwable t) {
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

    private void getCatalogFilm(){
        if(intIdxGenre == movie.genres.genres.size())
            OpenApp();
        else
            getFilm();
    }

    private void OpenApp(){
        Intent minhaAcao = new Intent(acMain,Main.class);
        minhaAcao.putExtra("objFILME",movie);
        startActivity(minhaAcao);
        finish();
    }

}
