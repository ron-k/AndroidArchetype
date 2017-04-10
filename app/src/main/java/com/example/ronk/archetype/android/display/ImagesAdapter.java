package com.example.ronk.archetype.android.display;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesListItemBinding;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by ronk on 10/04/2017.
 */

class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.Holder> {

    private final Presentor presentor;
    private final Picasso picasso;

    @Inject
    ImagesAdapter(@NonNull Presentor presentor, @NonNull Picasso picasso) {
        this.presentor = presentor;
        this.picasso = picasso;
    }


    @Override
    public ImagesAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        ActivityDisplayImagesListItemBinding layout = ActivityDisplayImagesListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(layout, picasso);
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.Holder holder, int position) {
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

    static class Holder extends RecyclerView.ViewHolder {
        private final ActivityDisplayImagesListItemBinding layout;
        private final Picasso picasso;

        Holder(ActivityDisplayImagesListItemBinding layout, Picasso picasso) {
            super(layout.getRoot());
            this.layout = layout;
            this.picasso = picasso;
        }

        void bind(DisplayedEntity e) {
            picasso.load(e.imageUrl()).into(layout.img);
        }
    }
}
