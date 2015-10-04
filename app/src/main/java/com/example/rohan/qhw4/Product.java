package com.example.rohan.qhw4;

import java.io.Serializable;

/**
 * Created by rohan on 10/2/15.
 */


//For each item we are interested in the price, currency, small and large images, artist (label),
// title (label), link (url) to media, Category (label), and release date (label).
public class Product implements Serializable {

    String titleLabel, currency, artistLabel, linkUrl, summary, duration;
    String categoryLabel, releaseDateLabel, smallImage, largeImage;
    double price;


    public String getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(String titleLabel) {
        this.titleLabel = titleLabel;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getArtistLabel() {
        return artistLabel;
    }

    public void setArtistLabel(String artistLabel) {
        this.artistLabel = artistLabel;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public void setCategoryLabel(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    public String getReleaseDateLabel() {
        return releaseDateLabel;
    }

    public void setReleaseDateLabel(String releaseDateLabel) {
        this.releaseDateLabel = releaseDateLabel;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "titleLabel='" + titleLabel + '\'' +
                ", currency='" + currency + '\'' +
                ", artistLabel='" + artistLabel + '\'' +
                ", linkUrl='" + linkUrl + '\'' +
                ", summary='" + summary + '\'' +
                ", duration='" + duration + '\'' +
                ", categoryLabel='" + categoryLabel + '\'' +
                ", releaseDateLabel='" + releaseDateLabel + '\'' +
                ", smallImage='" + smallImage + '\'' +
                ", largeImage='" + largeImage + '\'' +
                ", price=" + price +
                '}';
    }
}
