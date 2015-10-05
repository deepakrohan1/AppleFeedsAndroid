package com.example.rohan.qhw4;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
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

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class MediaListActivity extends AppCompatActivity {
    LinearLayout fMainLayout;
    String mediaTypeSelected, mediaTypeUrl;
    final static String MEDIATYPE = "Media_Type";
    final static String MEDIAPRODUCTS = "Media_Products";
    ArrayList<Product> productsList;
    SharedPreferences preferences;
    AlertDialog.Builder builder;
    ProgressDialog jsonLoad;

    static final String iTUNESJSON = "iTunesJSON";
    static final String JSONVal = "JSONVal";
    static final String JSONTimetoReset = "JSONTimetoReset";


    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.media_icon);

        builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Entry")
                .setMessage("Are you sure you want to delete the \nentry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Demo", "check the ID" + id);
                        productsList.remove(id);
                        Log.d("Demo", productsList.get(id).toString());
                        for (Product p : productsList) {
                        }
                        toSharedPreference(productsList, getSharedPreferences(JSONVal + mediaTypeSelected, 0));
                        generateViews(productsList);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        fMainLayout = (LinearLayout) findViewById(R.id.insideScrollLinear);
        //Checking the intent
        if (checkIntent()) {
            mediaTypeUrl = getIntent().getExtras().getString(MainActivity.MEDIAURL);
            mediaTypeSelected = getIntent().getExtras().getString(MainActivity.MEDIATYPE);
            Log.d("Demo", "URL: " + mediaTypeUrl + "--" + mediaTypeSelected + "--");

            this.setTitle(mediaTypeSelected);
            new GetAsyncTask().execute(mediaTypeUrl,mediaTypeSelected);
        }
    }

    private class GetAsyncTask extends AsyncTask<String, Void, ArrayList<Product>> {
        ArrayList<Product> products;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonLoad = new ProgressDialog(MediaListActivity.this);
            jsonLoad.show();
            jsonLoad.setMessage("Loading...");
            jsonLoad.setTitle("Getting the Content");
            jsonLoad.setCancelable(false);
        }

        @Override
        protected void onPostExecute(ArrayList<Product> products) {
            super.onPostExecute(products);
            jsonLoad.dismiss();
            if (products != null) {
                productsList = (ArrayList<Product>) products.clone();
                generateViews(products);
            }
        }

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            try {
                preferences = getSharedPreferences(JSONVal + params[1], 0);
                String jsonString = preferences.getString(iTUNESJSON, "");
                long storedTime = preferences.getLong(JSONTimetoReset, 0);
                Time currentTime = new Time();
                currentTime.setToNow();
                if (jsonString.isEmpty() || (currentTime.toMillis(false) - storedTime) > 120000)

                {
                    URL url = new URL(params[0]);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = reader.readLine();
                        while (line != null) {
                            sb.append(line);
                            line = reader.readLine();
                        }
                        jsonString = sb.toString();
                        // putting value in shared preferences
                        currentTime = new Time();
                        currentTime.setToNow();

                    }

                    products = ProductUtil.ProductJSONParser.ProductParser(jsonString, mediaTypeSelected);
                    toSharedPreference(products, preferences);
                } else

                {
                    Gson gson = new Gson();
                    Type t = new TypeToken<ArrayList<Product>>() {
                    }.getType();
                    products = gson.fromJson(preferences.getString(iTUNESJSON, ""), t);
                }
                return products;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    //Generate Views
    private void generateViews(ArrayList<Product> productsLists) {
        if (fMainLayout.getChildCount() > 0) {
            fMainLayout.removeAllViews();
        }
        Log.d("Demo", productsLists.get(id).toString());


        if (productsLists != null) {
            id = 0;
            for (int i = 0; i < productsLists.size(); i++) {
                Log.d("Demo", productsList.get(id).toString());
                Log.d("Demo", "================================");
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
                final ImageView lImageViewAppIcon = new ImageView(MediaListActivity.this);
                lImageViewAppIcon.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                Picasso.with(MediaListActivity.this).load(productsLists.get(i).getLargeImage()).resize(150, 150).into(lImageViewAppIcon);

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
                textViewName.setText(productsLists.get(i).getTitleLabel());
                textViewName.setId(i);
                textViewName.setLongClickable(true);
                final Product product = productsLists.get(i);
                textViewName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentDetailed = new Intent(MediaListActivity.this, DetailedMediaActivity.class);
                        intentDetailed.putExtra(MEDIATYPE, mediaTypeSelected);
                        intentDetailed.putExtra(MEDIAPRODUCTS, product);
                        startActivity(intentDetailed);
                    }
                });

                textViewName.setOnLongClickListener(new View.OnLongClickListener() {

                    @Override
                    public boolean onLongClick(View v) {
                        id = textViewName.getId();
                        Log.d("Demo", "Pressed Key: " + id);
                        builder.create().show();
                        return true;

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

    private void toSharedPreference(ArrayList<Product> products, SharedPreferences preferences) {
        Gson gson = new Gson();
        Time currentTime = new Time();
        currentTime.setToNow();
        String serializedStr = gson.toJson(products);
        preferences.edit().clear();
        preferences.edit()
                .putString(iTUNESJSON, serializedStr).putLong(JSONTimetoReset, currentTime.toMillis(false))
                .commit();
    }
}
