package com.ronk.ex20220714.flickr_gallery.single;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ronk.ex20220714.flickr_gallery.gallery.GalleryItem;

public class FlickrItemActivity extends AppCompatActivity {
    public static final String EXTRA_ITEM = "FlickrItemActivity_EXTRA_ITEM";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GalleryItem item = getIntent().getParcelableExtra(EXTRA_ITEM);
        startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(item.getThumbnailUrl())));
    }
}
