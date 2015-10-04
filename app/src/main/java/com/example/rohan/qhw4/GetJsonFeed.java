package com.example.rohan.qhw4;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rohan on 10/3/15.
 */
public class GetJsonFeed extends AsyncTask<String, Void, ArrayList<Product>> {
    IGetFeeds Activity;
    String mediaType;
    ProgressDialog jsonLoad;

    public GetJsonFeed(IGetFeeds activity, String mediaType) {
        this.Activity = activity;
        this.mediaType = mediaType;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        jsonLoad = new ProgressDialog((Context) Activity);
        jsonLoad.show();
        jsonLoad.setMessage("Loading...");
        jsonLoad.setTitle("Getting the Content");
        jsonLoad.setCancelable(false);
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        super.onPostExecute(products);
        jsonLoad.dismiss();
        if (products.isEmpty()) {
            Log.d("Demo", "Not yet");
        } else {
//                generateViews(products);
            Activity.checkPreferences(products);
            for (Product s : products) {
//                Log.d("Demo", s.toString());
            }
        }
    }

    @Override
    protected ArrayList<Product> doInBackground(String... params) {
        BufferedReader br;
        StringBuilder sb = null;
//        if (isConnected()) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                return ProductUtil.ProductJSONParser.ProductParser(sb.toString(),mediaType);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
//        Log.d("Demo", "the content " + sb.toString());

//        }
        return null;
    }


    public static interface IGetFeeds {
        public ArrayList<Product> checkPreferences(ArrayList<Product> products);
    }
}
