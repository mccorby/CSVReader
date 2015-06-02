package com.mccorby.csvreader.presentation.main.ui.transformation;

import android.graphics.Bitmap;

import com.mccorby.csvreader.presentation.Constants;
import com.squareup.picasso.Transformation;

/**
 * Created by JAC on 02/06/2015.
 */
public class ThumbnailTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        int targetWidth, targetHeight;
        double aspectRatio;

        if (source.getWidth() > source.getHeight()) {
            targetWidth = Constants.MAX_WIDTH;
            aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            targetHeight = (int) (targetWidth * aspectRatio);
        } else {
            targetHeight = Constants.MAX_HEIGHT;
            aspectRatio = (double) source.getWidth() / (double) source.getHeight();
            targetWidth = (int) (targetHeight * aspectRatio);
        }

        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
        if (result != source) {
            source.recycle();
        }
        return result;
    }

    @Override
    public String key() {
        return "Thumbnail transformation";
    }
}
