package com.mccorby.csvreader.datasource.parser;

import com.mccorby.csvreader.domain.entities.CustomerImage;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public interface CustomerImageParser {

    List<CustomerImage> parse(String filename) throws ParsingException;
}
