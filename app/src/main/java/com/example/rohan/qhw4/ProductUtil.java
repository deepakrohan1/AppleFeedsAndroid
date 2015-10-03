package com.example.rohan.qhw4;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rohan on 10/2/15.
 */
public class ProductUtil {
    static public class ProductJSONParser {
        static ArrayList<Product> ProductParser(String s) throws JSONException {
            ArrayList<Product> productsList = new ArrayList<>();
            Log.d("Demo", "Inside UTIL");
            JSONObject root = new JSONObject(s).getJSONObject("feed");
            JSONArray entryArray = root.getJSONArray("entry");
            for (int i = 0; i < entryArray.length(); i++) {
                Product product = new Product();
//                Log.d("Demo", "Inside Entry Array");
                String label = entryArray.getJSONObject(i).getJSONObject("title").getString("label");
                product.setTitleLabel(label);
                Double imPrice = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getDouble("amount");
                product.setPrice(imPrice);
                String currency = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getString("currency");
                product.setCurrency(currency);
                String artist = entryArray.getJSONObject(i).getJSONObject("im:artist").getString("label");
                product.setArtistLabel(artist);
                String category = entryArray.getJSONObject(i).getJSONObject("category").getJSONObject("attributes").getString("label");
                product.setCategoryLabel(category);
                String releaseDate = entryArray.getJSONObject(i).getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");
                product.setReleaseDateLabel(releaseDate);
                String productLink = "";
//                JSONArray productLi = entryArray.getJSONArray(i).getJSONArray("link");
                JSONObject link = entryArray.getJSONObject(i);
                JSONArray links = link.getJSONArray("link");
                for (int j = 0; j < links.length() - 1; j++) {
                    productLink = links.getJSONObject(j).getJSONObject("attributes").getString("href");
//                    Log.d("Demo",productLink);
                    product.setLinkUrl(productLink);
                }
                String imageSmall = "";
                String imageLarge = "";
                JSONArray images = link.getJSONArray("im:image");
                for (int k = 0; k < 1; k++) {
                    imageSmall = images.getJSONObject(0).getString("label");
                    imageLarge = images.getJSONObject(2).getString("label");
                    product.setSmallImage(imageSmall);
                    product.setLargeImage(imageLarge);
//                    Log.d("Demo", imageSmall + "--" + imageLarge);
                }
//                Log.d("Demo",label+"--"+imPrice+"--"+currency+"--"+artist+"--"+category+"--"+releaseDate+"--"+productLink);
//                Log.d("Demo",imageSmall+"--"+imageLarge);
//                Log.d("Demo","-----------------------------");
                productsList.add(product);

            }
            return productsList;
        }
    }
}
