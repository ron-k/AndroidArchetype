package com.example.ronk.archetype.android.display;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesImageItemBinding;
import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesVideoItemBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by ronk on 10/04/2017.
 */

class ImagesAdapter extends RecyclerView.Adapter<BaseHolder> {

    static final int TYPE_IMAGE = 0;
    static final int TYPE_VIDEO = 1;
    private final Presentor presentor;
    private final Picasso picasso;

    @Inject
    ImagesAdapter(@NonNull Presentor presentor, @NonNull Picasso picasso) {
        this.presentor = presentor;
        this.picasso = picasso;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_IMAGE: {
                ActivityDisplayImagesImageItemBinding layout = ActivityDisplayImagesImageItemBinding.inflate(inflater, parent, false);
                return new ImageHolder(layout, picasso);
            }
            case TYPE_VIDEO: {
                ActivityDisplayImagesVideoItemBinding layout = ActivityDisplayImagesVideoItemBinding.inflate(inflater, parent, false);
                return new VideoHolder(layout, context);
            }
            default:
                throw new AssertionError();

        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        DisplayedEntity e = presentor.getItemAt(position);
        holder.bind(e);
    }

    @Override
    public int getItemCount() {
        return presentor.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return presentor.getItemViewType(position);
    }

}
