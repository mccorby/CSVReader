package com.mccorby.csvreader.domain.repository;

import com.mccorby.csvreader.domain.entities.CustomerImage;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public interface CustomerImageRepository {

    List<CustomerImage> getImages();
}
