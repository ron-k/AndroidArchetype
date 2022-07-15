package com.ronk.ex20220714.flickr_gallery.api;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public class Photos {
    public int page;
    public int pages;
    public int perpage;
    public int total;
    public List<Photo> photo;
}
