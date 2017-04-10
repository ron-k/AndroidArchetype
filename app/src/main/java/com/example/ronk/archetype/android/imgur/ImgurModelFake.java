package com.example.ronk.archetype.android.imgur;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ronk on 10/04/2017.
 */

public class ImgurModelFake implements ImgurModel {


    private static final List<String> LIST = Arrays.asList("http://i.imgur.com/VuRiO5e.jpg",
            "http://i.imgur.com/cCIb5sM.jpg",
            "http://i.imgur.com/oqIjOs1.jpg",
            "http://i.imgur.com/ZdVtqKT.jpg",
            "http://i.imgur.com/qdPo4TF.jpg");

    @NonNull
    @Override
    public Call<List<String>> getImages() {
        return new Call<List<String>>() {

            @Override
            public Response<List<String>> execute() {
                return Response.success(LIST);
            }

            @Override
            public void enqueue(Callback<List<String>> callback) {
                callback.onResponse(this, execute());
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

            @Override
            public Call<List<String>> clone() {
                return this;
            }

            @Override
            public Request request() {
                return null;
            }
        };
    }
}
