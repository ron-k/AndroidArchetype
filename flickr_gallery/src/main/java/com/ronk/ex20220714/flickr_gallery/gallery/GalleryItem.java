package com.ronk.ex20220714.flickr_gallery.gallery;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class GalleryItem implements Parcelable {
    private final String thumbnailUrl;
    private final String id;

    public GalleryItem(String thumbnailUrl, String id) {
        this.thumbnailUrl = thumbnailUrl;
        this.id = id;
    }

    protected GalleryItem(Parcel in) {
        thumbnailUrl = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thumbnailUrl);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GalleryItem that = (GalleryItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static final Creator<GalleryItem> CREATOR = new Creator<>() {
        @Override
        public GalleryItem createFromParcel(Parcel in) {
            return new GalleryItem(in);
        }

        @Override
        public GalleryItem[] newArray(int size) {
            return new GalleryItem[size];
        }
    };
}
