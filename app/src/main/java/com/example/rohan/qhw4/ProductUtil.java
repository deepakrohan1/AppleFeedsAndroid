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
        static ArrayList<Product> ProductParser(String s, String mediaType) throws JSONException {
            ArrayList<Product> productsList = new ArrayList<>();
            Log.d("Demo", "Inside UTIL : " +mediaType);
            JSONObject root = new JSONObject(s).getJSONObject("feed");
            JSONArray entryArray = root.getJSONArray("entry");

            for (int i = 0; i < entryArray.length(); i++) {
                Product product = new Product();
//                Log.d("Demo", "Inside Entry Array");
                //Title of App
                String label = entryArray.getJSONObject(i).getJSONObject("title").getString("label");
                product.setTitleLabel(label);

                //Price of the App
                Double imPrice = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getDouble("amount");
                product.setPrice(imPrice);

                //Currency
                String currency = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getString("currency");
                product.setCurrency(currency);

                //Artist
                String artist = entryArray.getJSONObject(i).getJSONObject("im:artist").getString("label");
                product.setArtistLabel(artist);

                //Category
                String category = entryArray.getJSONObject(i).getJSONObject("category").getJSONObject("attributes").getString("label");
                product.setCategoryLabel(category);

                //Release Date
                String releaseDate = entryArray.getJSONObject(i).getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");
                product.setReleaseDateLabel(releaseDate);

                //Summary of App
                if((mediaType.equals("BOOKS")) || (mediaType.equals("MACAPPS")) ||
                        (mediaType.equals("TVSHOWS")) || (mediaType.equals("ITUNESU"))
                        || (mediaType.equals("PODCASTS"))) {
                    try{
                        String summary = entryArray.getJSONObject(i).getJSONObject("summary").getString("label");
                        product.setSummary(summary);

                    }catch (JSONException e){
                        product.setSummary("Not Found");
                    }

                }
                JSONObject link = entryArray.getJSONObject(i);
                //Parsing the Preview Links
                if((mediaType.equals("AUDIOBOOKS")) || (mediaType.equals("MOVIES")) ||
                        (mediaType.equals("TVSHOWS")) || (mediaType.equals("MUSICVIDEO"))) {

                    JSONArray links = link.getJSONArray("link");

                    product.setLinkUrl(links.getJSONObject(0).getJSONObject("attributes").getString("href"));
                    product.setDuration(links.getJSONObject(1).getJSONObject("im:duration").getString("label"));
                }
                else {
                    String previewLink = entryArray.getJSONObject(i).getJSONObject("link").getJSONObject("attributes").getString("href");
                    product.setLinkUrl(previewLink);
                }

                //Image Links
                String imageSmall = "";
                String imageLarge = "";
                JSONArray images = link.getJSONArray("im:image");
                for (int k = 0; k < 1; k++) {
                    imageSmall = images.getJSONObject(0).getString("label");
                    imageLarge = images.getJSONObject(2).getString("label");
                    product.setSmallImage(imageSmall);
                    product.setLargeImage(imageLarge);
                }
                productsList.add(product);

            }
            return productsList;
        }
    }
}
