package com.mccorby.csvreader.presentation.main;

import com.mccorby.csvreader.domain.entities.CustomerImage;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public interface MainView {

    void itemsAvailable(List<CustomerImage> items);
}
