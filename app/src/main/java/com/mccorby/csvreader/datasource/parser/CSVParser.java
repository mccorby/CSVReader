package com.mccorby.csvreader.datasource.parser;

import android.content.Context;

import com.mccorby.csvreader.domain.entities.CustomerImage;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public class CSVParser implements CustomerImageParser {

    private static final String TAG = CSVParser.class.getSimpleName();
    private final Context mContext;

    public CSVParser(Context context) {
        this.mContext = context;
    }

    @Override
    public List<CustomerImage> parse(String filename) throws ParsingException{

        FileInputStream fis = null;
        try {
            fis = mContext.openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ParsingException(e.getMessage());
        }


        Reader in = new InputStreamReader(fis);
        org.apache.commons.csv.CSVParser parser = null;
        try {
            parser = new org.apache.commons.csv.CSVParser(in, CSVFormat.EXCEL.withHeader());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ParsingException(e.getMessage());
        }

        List<CustomerImage> list = new ArrayList<>();
        for (CSVRecord record : parser) {
            String title = record.get("title");
            String description = record.get("description");
            String url = record.get("image");
            CustomerImage ci = new CustomerImage();
            ci.setTitle(title);
            ci.setDescription(description);
            ci.setImageUrl(url);
            list.add(ci);
        }
        return list;
    }
}
