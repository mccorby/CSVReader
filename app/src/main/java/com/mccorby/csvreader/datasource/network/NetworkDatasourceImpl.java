package com.mccorby.csvreader.datasource.network;

import android.content.Context;
import android.util.Log;

import com.mccorby.csvreader.BuildConfig;
import com.mccorby.csvreader.datasource.DatasourceException;
import com.mccorby.csvreader.datasource.parser.CustomerImageParser;
import com.mccorby.csvreader.datasource.parser.ParsingException;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.repository.datasources.NetworkDatasource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public class NetworkDatasourceImpl implements NetworkDatasource {

    private static final String TAG = NetworkDatasourceImpl.class.getSimpleName();
    private CustomerImageParser mParser;
    private final Context mContext;

    public NetworkDatasourceImpl(Context context, CustomerImageParser parser) {
        this.mParser = parser;
        this.mContext = context;
    }

    @Override
    public List<CustomerImage> getImages() throws DatasourceException {
        // Get the file from the endpoint
        String fileUrl = BuildConfig.CSV_URL;
        // TODO Define destination in gradle file
        String fileDestination = "local_cvs_file.csv";

        // Save the file
        getFileFromBackend(fileUrl, fileDestination);
        // Parse the local file
        try {
            return mParser.parse(fileDestination);
        } catch (ParsingException e) {
            e.printStackTrace();
            throw new DatasourceException(e.getMessage());
        }
    }

    /**
     * This method gets the file from the server and stores it in the destination.
     * Note that Android provides some other ways of getting a file (DownloadManager)
     * but it looks overkilling for the purpose of this test.
     * @param fileUrl
     * @param fileDestination
     */
    private void getFileFromBackend(String fileUrl, String fileDestination) throws DatasourceException{
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(fileUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // Expect HTTP 200
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                // Handle server error messages
                Log.d(TAG, "Response code was wrong " + connection.getResponseCode() + ": " + connection.getResponseMessage());
                throw new DatasourceException("No response from server");
            }
            // Get the file using the internal app filesystem
            input = connection.getInputStream();
            output = mContext.openFileOutput(fileDestination, Context.MODE_PRIVATE);

            byte data[] = new byte[4096];
            int count;
            while ((count = input.read(data)) != -1) {
                output.write(data, 0, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatasourceException(e.getMessage());
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
    }
}
