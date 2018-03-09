package com.example.nadler.filmes.setting;

import android.app.Application;
import android.content.Context;

public class ApplicationContext  extends Application {
    private static Context context;
    public void onCreate(){
        context=getApplicationContext();
    }

    public static Context getCustomAppContext(){
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
