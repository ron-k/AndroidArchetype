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
    private static final int VIDEO_POSITION = 2111;
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_VIDEO = 1;
    private final ImgurModel model;
    private final Callback<List<String>> callback;
    private final View view;
    private final List<String> entities = new ArrayList<>();


    @Inject
    PresentorImpl(@NonNull ImgurModel model, View view) {
        this.model = model;
        this.view = view;
        callback = new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                entities.clear();
                entities.addAll(response.body());
                PresentorImpl.this.view.notifyDataChanged();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        };
    }

    @NonNull
    @Override
    public DisplayedEntity getItemAt(int position) {
        if (position < VIDEO_POSITION) {
            String url = entities.get(position);
            return new DisplayedEntityImage(url);
        } else if (position == VIDEO_POSITION) {
            return new DisplayedEntityVideo();
        } else { // position > VIDEO_POSITION
            String url = entities.get(position - 1);
            return new DisplayedEntityImage(url);
        }
    }

    @Override
    public int getItemCount() {
        int realImagesCount = entities.size();
        return realImagesCount >= VIDEO_POSITION ? realImagesCount + 1 : realImagesCount;
    }

    @Override
    public int getItemViewType(int position) {
        return position != VIDEO_POSITION ? TYPE_IMAGE : TYPE_VIDEO;
    }

    @Override
    public void onActivityStarted() {
        model.getImages().enqueue(callback);
    }


    public interface View {
        void notifyDataChanged();
    }

}
