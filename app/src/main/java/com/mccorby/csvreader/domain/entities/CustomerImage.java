package com.mccorby.csvreader.domain.entities;

/**
 * This class represents images as defined by the customer business model.
 *
 * Created by JAC on 01/06/2015.
 */
public class CustomerImage {

    private String mTitle;
    private String mDescription;
    private String mImageUrl;


    @Override
    public String toString() {
        return "CustomerImage{" +
                "mTitle='" + mTitle + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }
}
