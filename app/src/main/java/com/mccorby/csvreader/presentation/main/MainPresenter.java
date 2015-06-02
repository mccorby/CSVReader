package com.mccorby.csvreader.presentation.main;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.domain.interactors.GetCustomerImages;
import com.mccorby.csvreader.presentation.Presenter;
import com.mccorby.csvreader.presentation.main.loader.CustomerImagesLoader;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public class MainPresenter implements Presenter,
        LoaderManager.LoaderCallbacks<List<CustomerImage>> {

    private static final String TAG = MainPresenter.class.getSimpleName();
    private static final int CUSTOMER_IMAGES_LOADER = 0;

    private final GetCustomerImages mInteractor;
    private final MainView mMainView;
    private final LoaderManager mLoaderManager;
    private final Context mContext;

    public MainPresenter(Context context, LoaderManager loaderManager, MainView mainView, GetCustomerImages interactor) {
        mLoaderManager = loaderManager;
        this.mMainView = mainView;
        this.mInteractor = interactor;
        mContext = context;
    }

    @Override
    public void onCreate() {
        mLoaderManager.initLoader(CUSTOMER_IMAGES_LOADER, null, this);
    }


    @Override
    public Loader<List<CustomerImage>> onCreateLoader(int id, Bundle args) {
        return new CustomerImagesLoader(mInteractor, mContext);
    }

    @Override
    public void onLoadFinished(Loader<List<CustomerImage>> loader, List<CustomerImage> data) {
        for (CustomerImage image : data) {
            Log.d(TAG, image.toString());
        }
        mMainView.itemsAvailable(data);
    }

    @Override
    public void onLoaderReset(Loader<List<CustomerImage>> loader) {
        mMainView.itemsAvailable(null);
    }
}
