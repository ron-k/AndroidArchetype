package com.ronk.ex20220714.flickr_gallery.gallery;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.ronk.ex20220714.flickr_gallery.api.FlickrRemoteApi;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class FlickrGalleryScope {
    private FlickrGalleryScope() {
    }

    @NonNull
    public static GalleryViewModel getViewModel(ViewModelStoreOwner owner) {
        GalleryViewModel viewModel = new ViewModelProvider(owner).get(GalleryViewModel.class);
        viewModel.initModel(FlickrGalleryScope::getRepository);
        return viewModel;
    }

    private static FlickrGalleryRepository getRepository() {
        return new FlickrGalleryResopitoryRemote(getApi());
//        return new FlickrGalleryRepositoryFake();
    }

    private static FlickrRemoteApi getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FlickrRemoteApi.class);
    }

    public static ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(4);
    }

    public static Picasso getImageLoader(Context context) {
        Picasso.Builder picassoBuilder = new Picasso.Builder(context);
        picassoBuilder.executor(getExecutor());
        return picassoBuilder.listener((picasso1, uri, exception) -> Timber.e(exception, "onImageLoadFailed: url=%s", uri)).build();
    }
}
