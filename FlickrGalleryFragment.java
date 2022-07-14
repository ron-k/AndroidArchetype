package com.ronk.ex20220714.flickr_gallery.;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ronk.ex20220714.flickr_gallery.R;

public class FlickrGalleryFragment extends Fragment {

    private FlickrGalleryViewModel mViewModel;

    public static FlickrGalleryFragment newInstance() {
        return new FlickrGalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flickr_gallery, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FlickrGalleryViewModel.class);
        // TODO: Use the ViewModel
    }

}