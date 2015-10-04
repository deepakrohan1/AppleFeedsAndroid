package com.example.rohan.qhw4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView textViewAudioBooks;
    TextView textViewBooks;
    TextView textViewiosApps;
    TextView textViewMacApps;
    TextView textViewMovies;
    TextView textViewItunesU;
    TextView textViewPodCast;
    TextView LargeTextTvShows;
    TextView textViewMusicVideo;
    View fMainLayout;
    final static String fMEDIA_LIST_ACTIVITY = "com.group6a_hw04.group6a_hw04.intent.action.IOSAPPS";
    final static String fMEDIA_TYPE = "Media_Type";
    final static String fMEDIA_URL = "Media URL";
    final String fMEDIA_PREFERENCE = "Media Preference";
    final static String fCHECK_PREFERENCE = "Check Preference";

    static SharedPreferences fshareMedia;
    static SharedPreferences.Editor fmediaEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get Started
        initialzeScreens();
//        fMainLayout = findViewById(R.id.linearLayoutMain);

        fshareMedia = getSharedPreferences(fMEDIA_PREFERENCE, MODE_PRIVATE);
        fmediaEditor = fshareMedia.edit();
        fmediaEditor.clear();
        fmediaEditor.commit();


        //OnClicks
        textViewAudioBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSharedPref = checkSharedPreference("AUDIOBOOKS");
                startIntent("AUDIOBOOKS","https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json",isSharedPref);

//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json");
//                i.putExtra(TYPE,"AUDIOBOOKS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSharedPref = checkSharedPreference("BOOKS");
                startIntent("BOOKS","https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json",isSharedPref);
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json");
//                i.putExtra(TYPE,"BOOKS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewiosApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSharedPref = checkSharedPreference("IOSAPPS");
                startIntent("IOSAPPS","https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json",isSharedPref);

//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/newapplications/limit=25/json");
//                i.putExtra(TYPE,"IOSAPPS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewMacApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSharedPref = checkSharedPreference("MACAPPS");
                startIntent("MACAPPS","https://itunes.apple.com/us/rss/topfreemacapps/limit=25/json",isSharedPref);

//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topfreemacapps/limit=25/json");
//                i.putExtra(TYPE,"MACAPPS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isSharedPref = checkSharedPreference("MOVIES");
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topmovies/limit=25/json");
//                i.putExtra(TYPE,"MOVIES");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewItunesU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isSharedPref = checkSharedPreference("ITUNESU");
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topitunesucollections/limit=25/json");
//                i.putExtra(TYPE,"ITUNESU");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        textViewMusicVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isSharedPref = checkSharedPreference("PODCASTS");
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/topmusicvideos/limit=25/json");
//                i.putExtra(TYPE,"MUSICVIDEO");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });



        textViewPodCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isSharedPref = checkSharedPreference("PODCASTS");
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/toppodcasts/limit=25/json");
//                i.putExtra(TYPE,"PODCASTS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });

        LargeTextTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                boolean isSharedPref = checkSharedPreference("TVSHOWS");
//                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
//                i.putExtra(URL,"https://itunes.apple.com/us/rss/toptvepisodes/limit=25/json");
//                i.putExtra(TYPE,"TVSHOWS");
//                i.putExtra(ISSHARED,isSharedPref);
//                startActivity(i);
            }
        });


    }

    private void startIntent(String type, String url, boolean isShared){
        Intent i = new Intent(MainActivity.this, MediaListActivity.class);
        i.putExtra(fMEDIA_URL,url);
        i.putExtra(fMEDIA_TYPE,type);
        i.putExtra(fCHECK_PREFERENCE,isShared);
        startActivity(i);
    }

    private void initialzeScreens(){
        textViewAudioBooks = (TextView)findViewById(R.id.textViewAudioBooks);
        textViewBooks = (TextView)findViewById(R.id.textViewBooks);
        textViewiosApps = (TextView)findViewById(R.id.textViewiosApps);
        textViewMacApps = (TextView)findViewById(R.id.textViewMacApps);
        textViewMovies = (TextView)findViewById(R.id.textViewMovies);
        textViewItunesU = (TextView)findViewById(R.id.textViewItunesU);
        textViewPodCast = (TextView)findViewById(R.id.textViewPodCast);
        textViewMusicVideo = (TextView)findViewById(R.id.textViewMusicVideo);
        LargeTextTvShows = (TextView)findViewById(R.id.LargeTextTvShows);
    }

    public boolean checkSharedPreference(String amediaType){

        boolean lcheck = fshareMedia.contains(fMEDIA_TYPE);
        if(lcheck) {
            String lcheckMedia = fshareMedia.getString(fMEDIA_TYPE,null);
            if(lcheckMedia!=null && lcheckMedia.equals(amediaType)){
                return true;
            }
            else return false;
        } else {
            putSharedPreference(amediaType);
            return false;
        }
    }

    public void putSharedPreference(String amediaType){
        fshareMedia.edit().putString(fMEDIA_TYPE,amediaType).apply();

        Timer ltimer = new Timer();
        ltimer.schedule(new TimerTask() {
            @Override
            public void run() {
                fshareMedia.edit().clear().apply();
            }
        },2*60*1000);
    }


}
