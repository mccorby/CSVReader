package com.mccorby.csvreader.repository.datasources;

import com.mccorby.csvreader.domain.entities.CustomerImage;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public interface CacheDatasource {

    List<CustomerImage> getImages();
    CustomerImage getImage(Integer id);
    void addImages(List<CustomerImage> imageList);
    void addImage(CustomerImage image);

}
