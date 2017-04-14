package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;

import com.example.ronk.archetype.android.imgur.ImgurModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ronk on 10/04/2017.
 */

class PresentorImpl implements Presentor {
    private static final int VIDEO_POSITION = 2;
    private final ImgurModel model;
    private final Callback<List<String>> callback;
    private final View view;


    @Inject
    PresentorImpl(@NonNull ImgurModel model, View view) {
        this.model = model;
        this.view = view;
        callback = new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                PresentorImpl.this.view.render(createViewState(response));
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        };
    }

    @NonNull
    private ViewState createViewState(Response<List<String>> response) {
        List<DisplayedEntity> displayedEntities = new ArrayList<>();
        List<String> rawEntities = response.body();
        int itemsCount = getItemsCount(rawEntities);
        for (int i = 0; i < itemsCount; i++) {
            displayedEntities.add(createEntityAt(i, rawEntities));
        }
        return new ViewState(displayedEntities);
    }

    private int getItemsCount(List<String> entities) {
        int imagesCount = entities.size();
        return imagesCount >= VIDEO_POSITION ? imagesCount + 1 : imagesCount;
    }

    @NonNull
    private DisplayedEntity createEntityAt(int position, List<String> rawEntities) {
        if (position < VIDEO_POSITION) {
            String url = rawEntities.get(position);
            return new DisplayedEntityImage(url);
        } else if (position == VIDEO_POSITION) {
            return new DisplayedEntityVideo();
        } else { // position > VIDEO_POSITION
            String url = rawEntities.get(position - 1);
            return new DisplayedEntityImage(url);
        }
    }

    @Override
    public void onActivityStarted() {
        model.getImages().enqueue(callback);
    }


}
