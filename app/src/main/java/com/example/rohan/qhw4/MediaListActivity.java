package com.example.rohan.qhw4;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MediaListActivity extends AppCompatActivity implements GetJsonFeed.IGetFeeds {
    LinearLayout fMainLayout;
    String mediaTypeSelected, mediaTypeUrl;
    final static String MEDIATYPE = "Media_Type";
    final static String MEDIAPRODUCTS = "Media_Products";
    final String MEDIAPREFERENCE = "Media Preference";
    int count = 0;
    int id = 0;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.media_icon);

        fMainLayout = (LinearLayout) findViewById(R.id.insideScrollLinear);
        //Checking the intent
        if (checkIntent()) {
            mediaTypeUrl = getIntent().getExtras().getString(MainActivity.MEDIAURL);
            mediaTypeSelected = getIntent().getExtras().getString(MainActivity.MEDIATYPE);
            boolean lcheckPreference = getIntent().getExtras().getBoolean(MainActivity.ISPREFERENCE);
            Log.d("Demo", "URL: " + mediaTypeUrl + "--" + mediaTypeSelected + "--" + lcheckPreference);

            this.setTitle(mediaTypeSelected);
            if (lcheckPreference) {
                SharedPreferences lshareMedia = getSharedPreferences(MEDIAPREFERENCE, MODE_PRIVATE);
                String lcheckFeeds = lshareMedia.getString(MEDIAPRODUCTS, null);
                Gson lgson1 = new Gson();
                Type type = new TypeToken<List<Product>>() {
                }.getType();
                ArrayList<Product> products = lgson1.fromJson(lcheckFeeds, type);
                for (Product p : products) {
//                    Log.d("Demo", "Stored" + p.toString());
                }
                generateViews(products);
            } else {

                new GetJsonFeed(this, mediaTypeSelected).execute(mediaTypeUrl);
            }
        }


    }

    @Override
    public void checkPreferences(ArrayList<Product> products) {
//        for (Product p : products) {
//            Log.d("Demo", p.toString());
//        }

        final SharedPreferences lshareMedia = getSharedPreferences(MEDIAPREFERENCE, MODE_PRIVATE);

        String lcheckMedia = lshareMedia.getString(MEDIATYPE, null);
        Gson lgson = new Gson();
        Type ltype = new TypeToken<List<Product>>() {
        }.getType();
        String lstringObjects = lgson.toJson(products, ltype);

        if (lcheckMedia == null || !lcheckMedia.equals(mediaTypeSelected)) {

            lshareMedia.edit().putString(MEDIATYPE, mediaTypeSelected).apply();

            Timer ltimer = new Timer();
            ltimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    lshareMedia.edit().clear().apply();
                }
            }, 2 * 60 * 1000);
        }
        lshareMedia.edit().putString(MEDIAPRODUCTS, lstringObjects).apply();
        generateViews(products);


    }


    //Generate Views
    private void generateViews(ArrayList<Product> productsLists) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete the \nentry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        new DeleteAll().execute("http://dev.theappsdr.com/apis/trivia_fall15/deleteAll.php");
                        int flag = 1;
                        Log.d("Demo","check the ID"+id);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        if (productsLists != null) {
            count = 0;
            id = 0;
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
                final TextView textViewName = new TextView(MediaListActivity.this);
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
                textViewName.setId(count);

                textViewName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentDetailed = new Intent(MediaListActivity.this, DetailedMediaActivity.class);
                        intentDetailed.putExtra(MEDIATYPE, mediaTypeSelected);
                        intentDetailed.putExtra(MEDIAPRODUCTS, product);
                        startActivity(intentDetailed);
//                        startDetailedActivity(mediaTypeSelected, feed);
                    }
                });

                textViewName.setOnLongClickListener(new View.OnLongClickListener(){

                    @Override
                    public boolean onLongClick(View v) {
                        id = textViewName.getId();
                        Log.d("Demo","Pressed Key: "+id);
                        builder.create().show();
                        return false;

                    }
                });

                //Adding text view and image view into the master layout
                linearLayoutList.addView(lImageViewAppIcon);
                linearLayoutList.addView(textViewName);
                fMainLayout.addView(linearLayoutList);
                count ++;
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
