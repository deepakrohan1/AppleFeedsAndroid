package com.example.rohan.qhw4;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MediaListActivity extends AppCompatActivity {

    ProgressDialog jsonLoad;
    ArrayList<Product> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        jsonLoad = new ProgressDialog(MediaListActivity.this);
        //Checking the intent
        if(checkIntent()){
            String name = getIntent().getExtras().getString("URL");
            Log.d("URL", name);
        }


    }

    class GetAudioBooks extends AsyncTask<String, Void, ArrayList<Product>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonLoad.show();
            jsonLoad.setMessage("Loading...");
            jsonLoad.setTitle("Getting the Content");
            jsonLoad.setCancelable(false);
        }

        @Override
        protected void onPostExecute(ArrayList<Product> products) {
            super.onPostExecute(products);
            jsonLoad.dismiss();
            for(Product s : products) {
                Log.d("Demo", "After the Parsing Plist" + s.toString());
            }
        }

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            BufferedReader br;
            if(isConnected()){
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


            return null;
        }
    }



    public boolean checkIntent(){
        if(getIntent().getExtras() != null){
            return true;
        }
        else{
            return false;
        }
    }


    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(NETWORK_STATS_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }


    }
}