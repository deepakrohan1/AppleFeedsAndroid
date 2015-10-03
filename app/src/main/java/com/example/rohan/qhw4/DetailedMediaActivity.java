package com.example.rohan.qhw4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

        if(getIntent().getExtras() != null){
            textViewTitle.setText(getIntent().getExtras().getString("appName"));
            textViewReleaseDate.setText(getIntent().getExtras().getString("dateReleased"));
            textViewBy.setText("By: "+getIntent().getExtras().getString("artist"));
            textViewPrice.setText("Price: "+getIntent().getExtras().getDouble("price") + " "+getIntent().getExtras().getString("currency"));
            textViewCategory.setText("Category: "+getIntent().getExtras().getString("category"));
            textViewAppLink.setText(getIntent().getExtras().getString("link"));
            Picasso.with(this).load(getIntent().getExtras().getString("imageLarge")).into(imageViewApp);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detailed_media, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
