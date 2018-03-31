package com.iousave.www.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class Img {


    public static void setImg(ImageView imageView, String imgUrl, int placeholder, int err, String tag) {
        Transformation transformation = getTransformation(imageView);

        Picasso.get().load(imgUrl)
                .transform(transformation)
                .placeholder(placeholder)
                .error(err)
                .tag(tag)
                .into(imageView);


    }


    private static Transformation getTransformation(final ImageView imageView) {
        return new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {

                int targetWidth = imageView.getWidth() * 2;

                if (source.getWidth() == 0) {
                    return source;
                }

                if (source.getWidth() < targetWidth) {
                    return source;
                } else {
                    double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                    int targetHeight = (int) (targetWidth * aspectRatio);
                    if (targetHeight != 0 && targetWidth != 0) {
                        Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                        if (result != source) {
                            source.recycle();
                        }
                        return result;
                    } else {
                        return source;
                    }
                }

            }

            @Override
            public String key() {
                return "";
            }
        };
    }
}
