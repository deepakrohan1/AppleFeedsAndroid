package com.example.rohan.qhw4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    final static String MEDIATYPE = "Media_Type";
    final static String MEDIAURL = "Media URL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get Started
        initialzeScreens();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.media_icon);
        actionBar.setTitle("App Store Latest...");

        //OnClicks
        textViewAudioBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json");
                i.putExtra(MEDIATYPE,"AUDIO BOOKS");
                startActivity(i);
            }
        });

        textViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json");
                i.putExtra(MEDIATYPE,"BOOKS");
                startActivity(i);
            }
        });

        textViewiosApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/newapplications/limit=25/json");
                i.putExtra(MEDIATYPE,"iOS APPS");
                startActivity(i);
            }
        });

        textViewMacApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topfreemacapps/limit=25/json");
                i.putExtra(MEDIATYPE,"MAC APPS");
                startActivity(i);
            }
        });

        textViewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topmovies/limit=25/json");
                i.putExtra(MEDIATYPE,"MOVIES");
                startActivity(i);
            }
        });

        textViewItunesU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topitunesucollections/limit=25/json");
                i.putExtra(MEDIATYPE,"ITUNES U");
                startActivity(i);
            }
        });

        textViewMusicVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/topmusicvideos/limit=25/json");
                i.putExtra(MEDIATYPE,"MUSIC VIDEO");
                startActivity(i);
            }
        });

        textViewPodCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/toppodcasts/limit=25/json");
                i.putExtra(MEDIATYPE,"PODCASTS");
                startActivity(i);
            }
        });

        LargeTextTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra(MEDIAURL,"https://itunes.apple.com/us/rss/toptvepisodes/limit=25/json");
                i.putExtra(MEDIATYPE,"TV SHOWS");
                startActivity(i);
            }
        });


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



}
