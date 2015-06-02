package com.mccorby.csvreader.presentation.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mccorby.csvreader.R;
import com.mccorby.csvreader.domain.entities.CustomerImage;
import com.mccorby.csvreader.presentation.main.ui.transformation.ThumbnailTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by JAC on 01/06/2015.
 */
public class CustomerImageListAdapter extends RecyclerView.Adapter<CustomerImageListAdapter.ViewHolder> {

    private Context mContext;
    private List<CustomerImage> mImageList;

    public CustomerImageListAdapter(Context context, List<CustomerImage> list) {
        this.mImageList = list;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_image_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        CustomerImage image = mImageList.get(i);
        if (!TextUtils.isEmpty(image.getImageUrl())) {
            Picasso picasso = Picasso.with(mContext);
            picasso.setIndicatorsEnabled(true);
            picasso.load(image.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new ThumbnailTransformation())
                    .error(R.mipmap.ic_launcher)
                    .into(viewHolder.mImageView);

        }
        viewHolder.mTitle.setText(image.getTitle());
    }

    @Override
    public int getItemCount() {
        return mImageList != null ? mImageList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.item_customer_image_list_iv);
            mTitle = (TextView) itemView.findViewById(R.id.item_customer_image_list_title_tv);
        }
    }

    public void setImageList(List<CustomerImage> list) {
        this.mImageList = list;
    }
}
