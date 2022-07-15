package com.ronk.ex20220714.flickr_gallery.gallery;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ronk.ex20220714.flickr_gallery.databinding.GalleryItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public abstract class GalleryItemsAdapter extends RecyclerView.Adapter<GalleryItemsAdapter.ViewHolder> {
    private final List<GalleryItem> items = new ArrayList<>();
    private final GalleryViewModel.OnItemClickedCallback onItemClickedCallback;
    private final Picasso imageLoader;

    public GalleryItemsAdapter(GalleryViewModel.OnItemClickedCallback onItemClickedCallback, Picasso imageLoader) {
        this.onItemClickedCallback = onItemClickedCallback;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GalleryItemBinding layout = GalleryItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unbind();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @UiThread
    public void updateItems(List<GalleryItem> itemsUpdate) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback(new ArrayList<>(items), itemsUpdate), false);
        items.clear();
        items.addAll(itemsUpdate);

        diffResult.dispatchUpdatesTo(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @NonNull
        private final ImageView thumbnailView;

        private ViewHolder(@NonNull GalleryItemBinding itemView) {
            super(itemView.getRoot());
            thumbnailView = itemView.thumbnail;
        }

        public void bind(GalleryItem galleryItem) {
            int itemSize = getItemViewportSize();
            imageLoader
                    .load(galleryItem.getThumbnailUrl())
                    .placeholder(new ColorDrawable(Color.GRAY))
                    .resize(itemSize, itemSize)
                    .centerCrop()
                    .into(thumbnailView);

//            thumbnailView.setImageDrawable(new ColorDrawable(Color.GREEN));
            itemView.setOnClickListener(it -> onItemClickedCallback.onItemClicked(galleryItem));
        }

        public void unbind() {
            thumbnailView.setImageDrawable(null);
            itemView.setOnClickListener(null);

        }
    }

    protected abstract int getItemViewportSize();

    private static class DiffCallback extends DiffUtil.Callback {
        private final List<GalleryItem> itemsUpdate;
        private final List<GalleryItem> items;

        public DiffCallback(List<GalleryItem> items, List<GalleryItem> itemsUpdate) {
            this.itemsUpdate = itemsUpdate;
            this.items = items;
        }

        @Override
        public int getOldListSize() {
            return items.size();
        }

        @Override
        public int getNewListSize() {
            return itemsUpdate.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return items.get(oldItemPosition).getId().equals(itemsUpdate.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return items.get(oldItemPosition).equals(itemsUpdate.get(newItemPosition));
        }
    }
}
