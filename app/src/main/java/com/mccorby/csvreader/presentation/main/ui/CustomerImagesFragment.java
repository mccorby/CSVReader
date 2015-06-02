package com.mccorby.csvreader.presentation.main.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mccorby.csvreader.R;
import com.mccorby.csvreader.datasource.cache.MemoryCacheDatasourceImpl;
import com.mccorby.csvreader.datasource.network.NetworkDatasourceImpl;
import com.mccorby.csvreader.datasource.parser.CSVParser;
import com.mccorby.csvreader.datasource.parser.CustomerImageParser;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.domain.interactors.GetCustomerImages;
import com.mccorby.csvreader.domain.repository.CustomerImageRepository;
import com.mccorby.csvreader.presentation.error.ErrorHandler;
import com.mccorby.csvreader.presentation.error.ErrorHandlerImpl;
import com.mccorby.csvreader.presentation.main.MainPresenter;
import com.mccorby.csvreader.presentation.main.MainView;
import com.mccorby.csvreader.presentation.main.adapters.CustomerImageListAdapter;
import com.mccorby.csvreader.presentation.main.interactors.GetCustomerImagesImpl;
import com.mccorby.csvreader.repository.CustomerImageRepositoryImpl;
import com.mccorby.csvreader.repository.datasources.CacheDatasource;
import com.mccorby.csvreader.repository.datasources.NetworkDatasource;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerImagesFragment extends Fragment implements MainView {

    private static final String TAG = CustomerImagesFragment.class.getSimpleName();

    // TODO This member to be injected!
    MainPresenter mMainPresenter;
    ErrorHandler mErrorHandler;

    private CustomerImageListAdapter mAdapter;

    public CustomerImagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectObjects();
    }

    private void injectObjects() {
        mErrorHandler = new ErrorHandlerImpl(getActivity());
        CustomerImageParser parser = new CSVParser(getActivity());
        NetworkDatasource networkDatasource = new NetworkDatasourceImpl(getActivity(), parser);
        CacheDatasource cacheDatasource = new MemoryCacheDatasourceImpl();
        CustomerImageRepository repository = new CustomerImageRepositoryImpl(networkDatasource, cacheDatasource);
        GetCustomerImages getCustomerImagesInteractor = new GetCustomerImagesImpl(repository);
        mMainPresenter = new MainPresenter(getActivity(), getLoaderManager(), this, getCustomerImagesInteractor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_customer_images, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_customer_image_list_rv);
        // Use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new CustomerImageListAdapter(getActivity(), null);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        mMainPresenter.onCreate();

        return rootView;
    }

    @Override
    public void itemsAvailable(List<CustomerImage> items) {
        mAdapter.setImageList(items);
        mAdapter.notifyDataSetChanged();
        if (items.size() == 0) {
            mErrorHandler.displayError(getString(R.string.no_items_available));
        }
    }
}
