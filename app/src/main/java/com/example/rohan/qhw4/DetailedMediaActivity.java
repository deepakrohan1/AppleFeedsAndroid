package com.example.rohan.qhw4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailedMediaActivity extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewReleaseDate;
    ImageView imageViewApp;
    TextView textViewBy;
    TextView textViewPrice;
    TextView textViewCategory;
    TextView textViewAppLink;
    TextView textViewSummary;
    TextView textViewDuration;
    Product product = null;
    String mediaType = "";
    final static String URL="URL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_media);
        textViewTitle = (TextView)findViewById(R.id.textViewTitle);
        imageViewApp = (ImageView)findViewById(R.id.imageViewApp);
        textViewReleaseDate = (TextView)findViewById(R.id.textViewReleaseDate);
        textViewBy = (TextView)findViewById(R.id.textViewBy);
        textViewPrice = (TextView)findViewById(R.id.textViewPrice);
        textViewCategory = (TextView)findViewById(R.id.textViewCategory);
        textViewAppLink = (TextView)findViewById(R.id.textViewAppLink);
        textViewSummary = (TextView)findViewById(R.id.textViewSummary);
        textViewDuration = (TextView)findViewById(R.id.textViewDuration);

        if(getIntent().getExtras() != null){
            product = (Product)getIntent().getExtras().getSerializable(MediaListActivity.MEDIAPRODUCTS);
            mediaType = getIntent().getExtras().getString(MediaListActivity.MEDIATYPE);

            textViewTitle.setText(product.getTitleLabel());
            textViewReleaseDate.setText(product.getReleaseDateLabel());
            textViewBy.setText("By: "+product.getArtistLabel());
            textViewPrice.setText("Price: "+product.getPrice() + " "+product.getCurrency());
            textViewCategory.setText("Category: "+product.getCategoryLabel());
            textViewAppLink.setText(product.getLinkUrl());
            Picasso.with(this).load(product.getLargeImage()).into(imageViewApp);

            if(mediaType.equals("BOOKS")||mediaType.equals("ITUNESU")||mediaType.equals("MACAPPS")
                    ||mediaType.equals("PODCAST")||mediaType.equals("TVSHOWS")){
                textViewSummary.setText("Summary: "+product.getSummary());
            }
            if(mediaType.equals("AUDIOBOOKS")){
                textViewDuration.setText("Duration: "+product.getDuration());
            }
        }

        textViewAppLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailedMediaActivity.this,PreviewActivity.class);
                i.putExtra(URL,product.getLinkUrl());
                startActivity(i);
            }
        });
    }


}
