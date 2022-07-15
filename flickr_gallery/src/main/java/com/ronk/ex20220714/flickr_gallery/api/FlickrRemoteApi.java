package com.ronk.ex20220714.flickr_gallery.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrRemoteApi {
    //    https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&extras=url_s&api_key=aabca25d8cd75f676d3a74a72dcebf21&format=json&nojsoncallback=1
    @GET("services/rest/?method=flickr.photos.getRecent&extras=url_s&format=json&nojsoncallback=1")
    Call<Root> getRecentPhotos(@Query("api_key") String apiKey, @Query("page") int page);
}
