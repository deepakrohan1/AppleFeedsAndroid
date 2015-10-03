package com.example.rohan.qhw4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewAudioBooks;
    TextView textViewBooks;
    TextView textViewiosApps;
    TextView textViewMacApps;
    TextView textViewMovies;
    TextView textViewItunesU;
    TextView textViewMusicVideo;
    TextView textViewPodCast;
    TextView LargeTextTvShows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Get Started
        initialzeScreens();

        //OnClicks
        textViewAudioBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json");
                startActivity(i);
            }
        });

        textViewBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json");
                startActivity(i);
            }
        });

        textViewiosApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/newapplications/limit=25/json");
                startActivity(i);
            }
        });

        textViewMacApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topfreemacapps/limit=25/json");
                startActivity(i);
            }
        });

        textViewMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topmovies/limit=25/json");
                startActivity(i);
            }
        });

        textViewItunesU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topitunesucollections/limit=25/json");
                startActivity(i);
            }
        });

        textViewMusicVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json");
                startActivity(i);
            }
        });

        textViewPodCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/toppodcasts/limit=25/json");
                startActivity(i);
            }
        });

        LargeTextTvShows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MediaListActivity.class);
                i.putExtra("URL","https://itunes.apple.com/us/rss/toptvepisodes/limit=25/json");
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
        textViewMusicVideo = (TextView)findViewById(R.id.textViewMusicVideo);
        textViewPodCast = (TextView)findViewById(R.id.textViewPodCast);
        LargeTextTvShows = (TextView)findViewById(R.id.LargeTextTvShows);
    }


}
