package com.example.ronk.archetype.android.display;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesImageItemBinding;
import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesVideoItemBinding;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by ronk on 10/04/2017.
 */

class ImagesAdapter extends RecyclerView.Adapter<BaseHolder> {

    private final Picasso picasso;
    private VideoHolder videoHolder;
    private List<DisplayedEntity> entities = Collections.emptyList();

    @Inject
    ImagesAdapter(@NonNull Picasso picasso) {
        this.picasso = picasso;
    }


    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case DisplayedEntity.TYPE_IMAGE: {
                ActivityDisplayImagesImageItemBinding layout = ActivityDisplayImagesImageItemBinding.inflate(inflater, parent, false);
                return new ImageHolder(layout, picasso);
            }
            case DisplayedEntity.TYPE_VIDEO: {
                if (videoHolder == null) {
                    ActivityDisplayImagesVideoItemBinding layout = ActivityDisplayImagesVideoItemBinding.inflate(inflater, parent, false);
                    videoHolder = new VideoHolder(layout, context);

                }
                return videoHolder;
            }
            default:
                throw new AssertionError();

        }
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        DisplayedEntity e = getItemAt(position);
        holder.bind(e);
    }

    @NonNull
    private DisplayedEntity getItemAt(int position) {
        return entities.get(position);
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemAt(position).getItemViewType();
    }

    public void stopPlayback() {
        if (videoHolder != null) {
            videoHolder.stopPlayback();
        }
    }

    public void setData(List<DisplayedEntity> entities) {
        this.entities = entities;
    }
}
