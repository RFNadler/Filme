package com.example.nadler.filmes.service;

import com.example.nadler.filmes.R;
import com.example.nadler.filmes.repository.MOVIE;
import com.example.nadler.filmes.repository.genremovie;
import com.example.nadler.filmes.repository.genres;
import com.example.nadler.filmes.repository.moviedetail;
import com.example.nadler.filmes.setting.ApplicationContext;
import com.example.nadler.filmes.setting.CONSTANT;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    interface getConfig {
        @GET("configuration")
        Call<MOVIE> getConfig(@Query("api_key") String api_key);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .connectTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .writeTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .build();

        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationContext.getCustomAppContext().getString(R.string.urlAPI))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    interface getGenre {
        @GET("genre/movie/list")
        Call<genres> getGenre(@Query("api_key") String api_key,
                               @Query("language") String language);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .connectTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .writeTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .build();

        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationContext.getCustomAppContext().getString(R.string.urlAPI))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    interface getMovieByGenre {
        @GET("genre/{genreid}/movies")
        Call<genremovie> getMovieByGenre(@Path("genreid") int genreid,
                                        @Query("api_key") String api_key,
                                        @Query("language") String language,
                                        @Query("include_adult") String include_adult,
                                        @Query("sort_by") String sort_by);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .connectTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .writeTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .build();

        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationContext.getCustomAppContext().getString(R.string.urlAPI))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    interface getMovie {
        @GET("movie/{id}")
        Call<moviedetail> getMovie(@Path("id") long id,
                                   @Query("api_key") String api_key,
                                   @Query("language") String language);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .connectTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .writeTimeout(CONSTANT.TimeOut, TimeUnit.SECONDS)
                .build();

        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApplicationContext.getCustomAppContext().getString(R.string.urlAPI))
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

}
