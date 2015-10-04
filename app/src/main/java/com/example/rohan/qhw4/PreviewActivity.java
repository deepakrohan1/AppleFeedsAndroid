package com.example.rohan.qhw4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class PreviewActivity extends AppCompatActivity {
    String url;
    WebView  webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        if(getIntent().getExtras()!=null){
            url = getIntent().getExtras().getString(DetailedMediaActivity.URL);
            webView.loadUrl(url);
            finish();
        }
    }


}
