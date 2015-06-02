package com.mccorby.csvreader.repository.datasources;

import com.mccorby.csvreader.datasource.DatasourceException;
import com.mccorby.csvreader.domain.entities.CustomerImage;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public interface NetworkDatasource {

    List<CustomerImage> getImages() throws DatasourceException;
}
