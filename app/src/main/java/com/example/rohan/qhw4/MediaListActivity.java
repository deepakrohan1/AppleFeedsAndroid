package com.example.rohan.qhw4;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MediaListActivity extends AppCompatActivity implements GetJsonFeed.IGetFeeds {
    LinearLayout fMainLayout;
    String fSelectedMediaType, fMediaTypeUrl;
    final static String fMEDIA_TYPE = "Media_Type";
    final static String fMEDIA_FEEDS = "Media_Feeds";
    final String fMEDIA_PREFERENCE = "Media Preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);
        fMainLayout = (LinearLayout) findViewById(R.id.insideScrollLinear);
        //Checking the intent
        if (checkIntent()) {
            fMediaTypeUrl = getIntent().getExtras().getString(MainActivity.fMEDIA_URL);
            fSelectedMediaType = getIntent().getExtras().getString(MainActivity.fMEDIA_TYPE);
            boolean lcheckPreference = getIntent().getExtras().getBoolean(MainActivity.fCHECK_PREFERENCE);
            Log.d("Demo", "URL: " + fMediaTypeUrl + "--" + fSelectedMediaType + "--" + lcheckPreference);

            this.setTitle(fMediaTypeUrl);
            if (lcheckPreference) {
                SharedPreferences lshareMedia = getSharedPreferences(fMEDIA_PREFERENCE, MODE_PRIVATE);
                String lcheckFeeds = lshareMedia.getString(fMEDIA_FEEDS, null);
                Gson lgson1 = new Gson();
                Type type = new TypeToken<List<Product>>() {
                }.getType();
                ArrayList<Product> products = lgson1.fromJson(lcheckFeeds, type);
                for (Product p : products) {
//                    Log.d("Demo", "Stored" + p.toString());
                }
                generateViews(products);
//                displayFeeds(lfeeds);
            } else {

                new GetJsonFeed(this, fSelectedMediaType).execute(fMediaTypeUrl);
            }
        }


    }

    @Override
    public void checkPreferences(ArrayList<Product> products) {
//        for (Product p : products) {
//            Log.d("Demo", p.toString());
//        }

        final SharedPreferences lshareMedia = getSharedPreferences(fMEDIA_PREFERENCE, MODE_PRIVATE);

        String lcheckMedia = lshareMedia.getString(fMEDIA_TYPE, null);
        Gson lgson = new Gson();
        Type ltype = new TypeToken<List<Product>>() {
        }.getType();
        String lstringObjects = lgson.toJson(products, ltype);

        if (lcheckMedia == null || !lcheckMedia.equals(fSelectedMediaType)) {

            lshareMedia.edit().putString(fMEDIA_TYPE,fSelectedMediaType).apply();

            Timer ltimer = new Timer();
            ltimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    lshareMedia.edit().clear().apply();
                }
            }, 2 * 60 * 1000);
        }
        lshareMedia.edit().putString(fMEDIA_FEEDS, lstringObjects).apply();
        generateViews(products);


    }


    //Generate Views
    private void generateViews(ArrayList<Product> productsLists) {
        if (productsLists != null) {
            for (final Product product : productsLists) {
                //Setting up master horizontal linear layout
                LinearLayout linearLayoutList = new LinearLayout(MediaListActivity.this);
                linearLayoutList.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams lLinearLayoutParams = (new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                lLinearLayoutParams.setMargins(10, 10, 10, 10);
                linearLayoutList.setLayoutParams(lLinearLayoutParams);

                //Setting up the image view
                ImageView lImageViewAppIcon = new ImageView(MediaListActivity.this);
                lImageViewAppIcon.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                Picasso.with(MediaListActivity.this).load(product.getLargeImage()).resize(150, 150).into(lImageViewAppIcon);

                //Setting up the text view and its layout params
                TextView textViewName = new TextView(MediaListActivity.this);
                LinearLayout.LayoutParams lTextViewLayoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                lTextViewLayoutParams.setMargins(35, 0, 0, 0);

                textViewName.setLayoutParams(lTextViewLayoutParams);
                textViewName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                textViewName.setTypeface(Typeface.DEFAULT);
                textViewName.setTextColor(Color.BLACK);
                textViewName.setClickable(true);
                textViewName.setText(product.getTitleLabel());
                textViewName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intentDetailed = new Intent(MediaListActivity.this, DetailedMediaActivity.class);
//                        intentDetailed.putExtra("MEDIATYPE", mediaType);
//                        intentDetailed.putExtra("PRODUCT", product);
//                        startActivity(intentDetailed);
//                        startDetailedActivity(fSelectedMediaType, feed);
                    }
                });

                //Adding text view and image view into the master layout
                linearLayoutList.addView(lImageViewAppIcon);
                linearLayoutList.addView(textViewName);
                fMainLayout.addView(linearLayoutList);
            }
        } else {
            Log.d("Demo", "No data Received");
            finish();
        }


    }
    public boolean checkIntent() {
        if (getIntent().getExtras() != null) {
            return true;
        } else {
            return false;
        }
    }

}
