package com.ronk.ex20220714.flickr_gallery.gallery;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ronk.ex20220714.flickr_gallery.R;

public class FlickrGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flickr_gallery);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FlickrGalleryFragment.newInstance())
                    .commitNow();
        }
    }
}