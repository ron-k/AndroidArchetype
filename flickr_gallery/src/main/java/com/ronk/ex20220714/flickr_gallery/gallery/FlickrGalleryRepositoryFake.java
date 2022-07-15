package com.ronk.ex20220714.flickr_gallery.gallery;

import androidx.annotation.NonNull;

import com.ronk.ex20220714.flickr_gallery.api.Photo;
import com.ronk.ex20220714.flickr_gallery.api.Photos;
import com.ronk.ex20220714.flickr_gallery.api.Root;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlickrGalleryRepositoryFake implements FlickrGalleryRepository {

    private static final String URL_1 = "https://live.staticflickr.com//65535//52216843315_f64d45339b_m.jpg#";
    private static final String URL_2 = "https://live.staticflickr.com//65535//52216843330_6f08112c82_m.jpg#";

    @Override
    public Call<Root> load(int page) {
        return new Call<>() {

            private final Response<Root> response = produceResponse(page);

            @NonNull
            @Override
            public Response<Root> execute() throws IOException {
                return response;
            }

            @Override
            public void enqueue(Callback<Root> callback) {
                callback.onResponse(this, response);
            }

            @Override
            public boolean isExecuted() {
                return true;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @NonNull
            @Override
            public Call<Root> clone() {
                return this;
            }

            @NonNull
            @Override
            public Request request() {
                return new Request.Builder().build();
            }

            @NonNull
            @Override
            public Timeout timeout() {
                return Timeout.NONE;
            }
        };
    }

    @NonNull
    private Response<Root> produceResponse(int page) {
        List<GalleryItem> photos = Arrays.asList(
                new GalleryItem(URL_1 + page, URL_1 + page),
                new GalleryItem(URL_2 + page, URL_2 + page)
        );

        Photos photos1 = new Photos();
        photos1.photo = Arrays.asList(producePhoto(2 * page + 1), producePhoto(2 * page + 1));
        Root root = new Root();
        root.photos = photos1;
        return Response.success(root);
    }

    @NonNull
    private Photo producePhoto(int page) {
        Photo photo = new Photo();
        photo.url_s = URL_1;
        return photo;
    }
}
