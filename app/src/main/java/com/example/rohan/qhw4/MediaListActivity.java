package com.example.rohan.qhw4;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MediaListActivity extends AppCompatActivity {
    LinearLayout startLayout;
    ScrollView scrollViewMediaList;
    ProgressDialog jsonLoad;
    ArrayList<Product> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        startLayout = (LinearLayout)findViewById(R.id.Inside);
        scrollViewMediaList = (ScrollView)findViewById(R.id.scrollViewMediaList);

        jsonLoad = new ProgressDialog(MediaListActivity.this);
        //Checking the intent
        if(checkIntent()){
            String name = getIntent().getExtras().getString("URL");
            Log.d("Demo", "URL: "+name);
            new GetAudioBooks().execute(name);
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
            if(products.isEmpty()){
                Log.d("Demo", "Not yet");
            }else {
//                generateViews(products);
                for (Product s : products) {
                    Log.d("Demo", s.toString());
                }
            }
        }

        @Override
        protected ArrayList<Product> doInBackground(String... params) {
            BufferedReader br;
            StringBuilder sb = null;
            if(isConnected()){
                try {
                    URL url = new URL(params[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = "";
                    sb = new StringBuilder();
                    while((line = br.readLine())!=null){
                        sb.append(line);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("Demo", "the content "+sb.toString());
                try {
                    if(params[0].equals("https://itunes.apple.com/us/rss/topfreeebooks/limit=25/json")){
                        Log.d("Demo","2");
                        return  ProductBookUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json")) {
                        Log.d("Demo","1");
                        return ProductUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/newapplications/limit=25/json")) {
                        Log.d("Demo","3");
                        return ProductBookUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/topfreemacapps/limit=25/json")) {
                        Log.d("Demo","4");
                        return ProductBookUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/topmovies/limit=25/json")) {
                        Log.d("Demo","5");
                        return ProductUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/topitunesucollections/limit=25/json")) {
                        Log.d("Demo","6");
                        return ProductBookUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/topaudiobooks/limit=25/json")) {
                        Log.d("Demo","7");
                        return ProductUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/toppodcasts/limit=25/json")) {
                        Log.d("Demo", "8");
                        return ProductBookUtil.ProductJSONParser.ProductParser(sb.toString());
                    }else if (params[0].equals("https://itunes.apple.com/us/rss/toptvepisodes/limit=25/json")) {
                        Log.d("Demo","9");
                        return ProductUtil.ProductJSONParser.ProductParser(sb.toString());
                    }
                } catch (JSONException e) {
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
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }
    //Generate Views
    private void generateViews(ArrayList<Product> productsLists){



//        RelativeLayout relativeLayout = new RelativeLayout(this);
        int count = 0;
        for (Product s : productsLists) {

//            setContentView(relativeLayout);
            Log.d("Demo", "After the Parsing Plist" + s.toString());
            count ++;
            ImageView imageView = new ImageView(this);
            Picasso.with(this).load(s.getSmallImage()).into(imageView);
            TextView textView = new TextView(this);
            textView.setText(s.getTitleLabel());
            textView.setId(count);
//            relativeLayout.addView(imageView);
//            relativeLayout.addView(textView);
            startLayout.addView(textView);


        }
//        scrollViewMediaList.addView(relativeLayout);
        startLayout.addView(scrollViewMediaList);
    }
}
