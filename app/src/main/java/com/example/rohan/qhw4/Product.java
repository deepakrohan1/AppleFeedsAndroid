package com.example.rohan.qhw4;

/**
 * Created by rohan on 10/2/15.
 */


//For each item we are interested in the price, currency, small and large images, artist (label),
// title (label), link (url) to media, Category (label), and release date (label).
public class Product {

    String titleLabel, smallImage, largeImage,currency,artistLabel, linkUrl, categoryLabel, releaseDateLabel;
    double price;

    public Product(String titleLabel, String smallImage, String largeImage, String currency, String artistLabel, String linkUrl, String categoryLabel, String releaseDateLabel, double price) {
        this.titleLabel = titleLabel;
        this.smallImage = smallImage;
        this.largeImage = largeImage;
        this.currency = currency;
        this.artistLabel = artistLabel;
        this.linkUrl = linkUrl;
        this.categoryLabel = categoryLabel;
        this.releaseDateLabel = releaseDateLabel;
        this.price = price;
    }

    //Getters


    public String getTitleLabel() {
        return titleLabel;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public String getCurrency() {
        return currency;
    }

    public String getArtistLabel() {
        return artistLabel;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public String getReleaseDateLabel() {
        return releaseDateLabel;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "titleLabel='" + titleLabel + '\'' +
                ", smallImage='" + smallImage + '\'' +
                ", largeImage='" + largeImage + '\'' +
                ", currency='" + currency + '\'' +
                ", artistLabel='" + artistLabel + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", categoryLabel='" + categoryLabel + '\'' +
                ", releaseDateLabel='" + releaseDateLabel + '\'' +
                ", price=" + price +
                '}';
    }
}