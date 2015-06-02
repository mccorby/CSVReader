package com.mccorby.csvreader.presentation.detail.ui;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorby.csvreader.R;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.presentation.Constants;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private CustomerImage mCustomerImage;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCustomerImage = getArguments().getParcelable(Constants.ARG_CUSTOMER_VIEW);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        buildUI(rootView);
        return rootView;
    }

    private void buildUI(View rootView) {
        // TODO Widgets in the view could be built using Butterknife
        TextView titleTv = (TextView) rootView.findViewById(R.id.fragment_detail_title_tv);
        TextView descriptionTv = (TextView) rootView.findViewById(R.id.fragment_detail_description_tv);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.fragment_detail_image_iv);

        titleTv.setText(mCustomerImage.getTitle());
        descriptionTv.setText(Html.fromHtml(mCustomerImage.getDescription()));
        if (!TextUtils.isEmpty(mCustomerImage.getImageUrl())) {
            Picasso.with(getActivity())
                    .load(mCustomerImage.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }


}
