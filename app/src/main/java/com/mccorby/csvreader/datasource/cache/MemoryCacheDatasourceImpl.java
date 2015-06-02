package com.mccorby.csvreader.datasource.cache;

import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.repository.datasources.CacheDatasource;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple caching datasource that just stores the data in memory.
 * This can be changed to some other mechanism as ContentProvider, file system
 * without affecting the rest of the app.
 * Created by JAC on 01/06/2015.
 */
public class MemoryCacheDatasourceImpl implements CacheDatasource {

    private List<CustomerImage> mCustomerImages;

    @Override
    public List<CustomerImage> getImages() {
        return mCustomerImages;
    }

    @Override
    public CustomerImage getImage(Integer id) {
        return null;
    }

    @Override
    public void addImages(List<CustomerImage> imageList) {
        if (mCustomerImages == null) {
            mCustomerImages = new ArrayList<>(imageList.size());
        }
        mCustomerImages.addAll(imageList);
    }

    @Override
    public void addImage(CustomerImage image) {
        if (mCustomerImages == null) {
            mCustomerImages = new ArrayList<>();
        }
        mCustomerImages.add(image);
    }
}
