package com.example.admin.thingstodo.Wine;


import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DescriptionAdapter extends PagerAdapter {

    Activity activity;
    List<String> images;

    DescriptionAdapter (Activity activity, List<String> images) {
        this.images = images;
        this.activity = activity;

    }

    @Override

    public int getCount() {

        return images.size();

    }


    @Override

    public boolean isViewFromObject(View view, Object object) {

        return view == object;

    }


    @Override

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(activity);

        //Sets the image url to the image
        Glide.with(activity).load(images.get(position)).into(imageView);

        container.addView(imageView, 0);

        return imageView;

    }


    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ImageView) object);

    }

}