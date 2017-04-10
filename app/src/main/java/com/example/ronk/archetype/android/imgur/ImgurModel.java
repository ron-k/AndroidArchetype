package com.example.ronk.archetype.android.imgur;

import android.support.annotation.NonNull;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ronk on 10/04/2017.
 */

public interface ImgurModel {
    @NonNull
    Call<List<String>> getImages();
}
