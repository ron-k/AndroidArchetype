package com.ronk.ex20220714.flickr_gallery.gallery;

import com.ronk.ex20220714.flickr_gallery.api.Root;

import retrofit2.Call;

public interface FlickrGalleryRepository {

    Call<Root> load(int page);
}
