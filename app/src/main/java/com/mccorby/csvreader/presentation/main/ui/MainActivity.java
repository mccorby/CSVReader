package com.mccorby.csvreader.presentation.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mccorby.csvreader.R;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.presentation.Constants;
import com.mccorby.csvreader.presentation.detail.ui.DetailActivity;

public class MainActivity extends AppCompatActivity implements CustomerImagesFragment.CustomerImageActionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CustomerImagesFragment())
                    .commit();
        }
    }

    @Override
    public void customerImageSelected(CustomerImage customerImage) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constants.ARG_CUSTOMER_VIEW, customerImage);
        startActivity(intent);
    }
}
