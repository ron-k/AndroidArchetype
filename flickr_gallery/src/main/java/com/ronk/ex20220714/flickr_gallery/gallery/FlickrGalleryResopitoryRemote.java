package com.ronk.ex20220714.flickr_gallery.gallery;

import com.ronk.ex20220714.flickr_gallery.api.FlickrRemoteApi;
import com.ronk.ex20220714.flickr_gallery.api.Root;

import retrofit2.Call;

public class FlickrGalleryResopitoryRemote implements FlickrGalleryRepository {
    private static final String API_KEY = "aabca25d8cd75f676d3a74a72dcebf21";

    private final FlickrRemoteApi api;

    public FlickrGalleryResopitoryRemote(FlickrRemoteApi api) {
        this.api = api;
    }

    @Override
    public Call<Root> load(int page) {
        return api.getRecentPhotos(API_KEY, page);
    }
}
