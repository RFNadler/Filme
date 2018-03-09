package com.example.nadler.filmes.repository;

import java.io.Serializable;

/**
 * Created by Nadler on 07/03/2018.
 */

public class results implements Serializable {
    public long id;
    public String original_title;
    public String poster_path;
    public String title;
    public float vote_average;
    public long vote_count;
}
