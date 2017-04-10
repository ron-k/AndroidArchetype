package com.example.ronk.archetype.android.display;

import com.example.ronk.archetype.android.databinding.ActivityDisplayImagesImageItemBinding;
import com.squareup.picasso.Picasso;

/**
 * Created by ronk on 10/04/2017.
 */
class ImageHolder extends BaseHolder {
    private final ActivityDisplayImagesImageItemBinding layout;
    private final Picasso picasso;

    ImageHolder(ActivityDisplayImagesImageItemBinding layout, Picasso picasso) {
        super(layout);
        this.layout = layout;
        this.picasso = picasso;
    }

    @Override
    public void bind(DisplayedEntity e) {
        picasso.load(e.imageUrl()).into(layout.img);
    }
}
