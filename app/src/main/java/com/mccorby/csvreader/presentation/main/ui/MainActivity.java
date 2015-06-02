package com.mccorby.csvreader.presentation.main.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mccorby.csvreader.R;

public class MainActivity extends AppCompatActivity {

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
}
