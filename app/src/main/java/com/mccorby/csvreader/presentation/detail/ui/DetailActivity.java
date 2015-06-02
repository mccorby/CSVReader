package com.mccorby.csvreader.presentation.detail.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.mccorby.csvreader.R;
import com.mccorby.csvreader.presentation.Constants;

public class DetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment fragment = new DetailFragment();

        if (getIntent().getExtras() != null) {
            Bundle args = new Bundle();
            args.putParcelable(Constants.ARG_CUSTOMER_VIEW, getIntent().getParcelableExtra(Constants.ARG_CUSTOMER_VIEW));
            fragment.setArguments(args);
        }

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
    }

}
