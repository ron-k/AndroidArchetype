package com.ronk.ex20220714.flickr_gallery.gallery;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.UiThread;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ronk.archetype.android.livedata.ComputableLiveData;
import com.ronk.ex20220714.flickr_gallery.api.Photo;
import com.ronk.ex20220714.flickr_gallery.api.Root;
import com.ronk.ex20220714.flickr_gallery.single.FlickrItemActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Response;
import timber.log.Timber;

public class GalleryViewModel extends ViewModel {

    private final ExecutorService executor;
    private final ComputableLiveData<List<GalleryItem>> loadMoreProcess;
    private FlickrGalleryRepository repository;
    private int page = 1;

    public GalleryViewModel() {
        executor = FlickrGalleryScope.getExecutor();
        loadMoreProcess = new ComputableLiveData<>(executor) {
            private final List<GalleryItem> items = new ArrayList<>();

            @Override
            protected List<GalleryItem> compute() {
                Timber.d("loading: page=%s", page);
                try {
                    Response<Root> response = repository.load(page).execute();
                    if (response.isSuccessful()) {
                        page++;
                        assert response.body() != null;
                        for (Photo photo : response.body().photos.photo) {
                            String url = photo.url_s;
                            if (url != null) {
                                items.add(new GalleryItem(url, url));
                            } else {
                                Timber.w("invalid item: photo=%s", photo);
                            }
                        }
                    } else {
                        throw new IOException("response is unsuccessful");
                    }
                } catch (IOException e) {
                    Timber.e(e);
                }
                return items;
            }
        };
    }

    interface OnItemClickedCallback {
        @UiThread
        void onItemClicked(GalleryItem item);

    }

    private final MutableLiveData<Consumer<Context>> navigation = new MutableLiveData<>();


    public void initModel(Supplier<FlickrGalleryRepository> repository) {
        if (this.repository == null) {
            this.repository = repository.get();
        }
    }

    public LiveData<Consumer<Context>> getNavigation() {
        return navigation;
    }

    public LiveData<List<GalleryItem>> getGalleryItems() {
        return loadMoreProcess.getLiveData();
    }

    public void needMore() {
        Timber.d("needMore: page=%s", page);
        loadMoreProcess.invalidate();
    }

    public void handleItemClicked(GalleryItem item) {
        navigate(context -> {
            Intent i = new Intent(context, FlickrItemActivity.class);
            i.putExtra(FlickrItemActivity.EXTRA_ITEM, item);
            context.startActivity(i);
        });
    }

    private void navigate(Consumer<Context> navigationDirective) {
        navigation.setValue(navigationDirective);
        navigation.setValue(null);
    }


}
