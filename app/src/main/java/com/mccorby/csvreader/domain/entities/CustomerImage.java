package com.mccorby.csvreader.domain.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This class represents images as defined by the customer business model.
 * Note that in a proper Clean Architecture there would be different models representing
 * the Customer Image, each depending on the needs of each layer.
 * For simplicity's sake all layers use the same model.
 *
 * Created by JAC on 01/06/2015.
 */
public class CustomerImage implements Parcelable{

    private String mTitle;
    private String mDescription;
    private String mImageUrl;


    /**
     * Required empty constructor
     */
    public CustomerImage() {

    }

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
    
       /*===========================
    Parcelable required methods
     */

    public static final Parcelable.Creator<CustomerImage> CREATOR
            = new Parcelable.Creator<CustomerImage>() {
        public CustomerImage createFromParcel(Parcel in) {
            return new CustomerImage(in);
        }

        public CustomerImage[] newArray(int size) {
            return new CustomerImage[size];
        }
    };

    private CustomerImage(Parcel in) {
        mDescription = in.readString();
        mImageUrl = in.readString();
        mTitle = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDescription);
        dest.writeString(mImageUrl);
        dest.writeString(mTitle);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
