package com.mccorby.csvreader.presentation.main.interactors;

import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.domain.interactors.GetCustomerImages;
import com.mccorby.csvreader.domain.repository.CustomerImageRepository;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public class GetCustomerImagesImpl implements GetCustomerImages {

    private CustomerImageRepository mImageRepository;

    public GetCustomerImagesImpl(CustomerImageRepository repository) {
        this.mImageRepository = repository;
    }

    @Override
    public List<CustomerImage> getCustomerImages() {
        return mImageRepository.getImages();
    }
}
