package com.darcess.instacaster.api;

import com.darcess.instacaster.api.post.Datum;
import com.darcess.instacaster.api.post.PostResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.darcess.instacaster.util.Global.INSTAGRAM_GET_COMMENTS;
import static com.darcess.instacaster.util.Global.INSTAGRAM_GET_MEDIA;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

public interface InstagramApiService {

    @GET(INSTAGRAM_GET_MEDIA)
    Observable<PostResponse> getPosts(@Query("lat") String latitude,
                                      @Query("lng") String longitude,
                                      @Query("distance") String distance,
                                      @Query("access_token") String instagramKey);
}
