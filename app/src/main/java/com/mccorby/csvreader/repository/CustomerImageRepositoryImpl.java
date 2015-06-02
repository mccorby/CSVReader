package com.mccorby.csvreader.repository;

import com.mccorby.csvreader.datasource.DatasourceException;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.domain.repository.CustomerImageRepository;
import com.mccorby.csvreader.repository.datasources.CacheDatasource;
import com.mccorby.csvreader.repository.datasources.NetworkDatasource;

import java.util.Collections;
import java.util.List;

/**
 * The repository provides logic on how to work with the datasources.
 *
 * Created by JAC on 01/06/2015.
 */
public class CustomerImageRepositoryImpl implements CustomerImageRepository {

    private NetworkDatasource mNetworkDatasource;
    private CacheDatasource mCacheDatasource;

    public CustomerImageRepositoryImpl(NetworkDatasource networkDatasource, CacheDatasource cacheDatasource) {
        this.mNetworkDatasource = networkDatasource;
        this.mCacheDatasource = cacheDatasource;
    }

    @Override
    public List<CustomerImage> getImages() {
        if (mCacheDatasource.getImages() == null || mCacheDatasource.getImages().size() == 0) {
            // First retrieve the list of images from the network
            List<CustomerImage> images = null;
            try {
                images = mNetworkDatasource.getImages();
                // Second add it to the cache system
                mCacheDatasource.addImages(images);
            } catch (DatasourceException e) {
                e.printStackTrace();
                return Collections.EMPTY_LIST;
            }
            // Return the list from the cache
        }
        return mCacheDatasource.getImages();
    }
}
