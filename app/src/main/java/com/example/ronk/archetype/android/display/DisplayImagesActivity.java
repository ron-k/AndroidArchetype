package com.example.ronk.archetype.android.display;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ronk.archetype.android.Application;
import com.example.ronk.archetype.android.R;
import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesBinding;
import com.example.ronk.archetype.android.imgur.ImgurModelFake;
import com.squareup.picasso.Picasso;

public class DisplayImagesActivity extends AppCompatActivity implements PresentorImpl.View {

    private ActivityDisplayImagesBinding layout;
    private RecyclerView.Adapter adapter;
    private Presentor presentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentor = getPresentor();
        adapter = getAdapter();
        setupLayout();
    }

    private void setupLayout() {
        layout = DataBindingUtil.setContentView(this, R.layout.activity_display_images);
        layout.images.setAdapter(adapter);
        layout.images.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presentor.onActivityStarted();
    }

    private RecyclerView.Adapter getAdapter() {
        Picasso picasso = Application.getInstance().getApplicationComponent().picasso();
        return new ImagesAdapter(presentor, picasso);
    }

    @NonNull
    private Presentor getPresentor() {
        return new PresentorImpl(new ImgurModelFake(), this);
    }

    @Override
    public void notifyDataChanged() {
        adapter.notifyDataSetChanged();
    }
}
