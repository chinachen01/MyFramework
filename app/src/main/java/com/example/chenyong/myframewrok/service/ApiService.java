package com.example.chenyong.myframewrok.service;


import com.example.chenyong.myframewrok.Model.AlbumModel;
import com.example.chenyong.myframewrok.Model.ResponseResult;
import com.example.chenyong.myframewrok.Model.User;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by focus on 16/8/16.
 */
public interface ApiService {
    @GET("/rest/album/list")
    Call<List<AlbumModel>> listAlbum();

    @GET("rest/album/list")
    Call<List<AlbumModel>> listAlbum(@QueryMap Map<String, String> category);

    @GET("rest/album/list")
    Call<List<AlbumModel>> listAlbum(@Query("Category") String category);

    @POST("rest/count/enter")
    Call<ResponseResult<Object>> foo(@Body User user);

    @POST("rest/count/enter")
    Call<ResponseResult<Object>> foo(@HeaderMap Map<String, String> users);

    @POST("rest/count/enter")
    Call<Object> fooT(@Body User user);

    @Multipart
    @PUT("rest/upload")
    Call<Object> upload(@PartMap Map<String, RequestBody> params);

    @GET("rest/album/list")
    Observable<List<AlbumModel>> rxListAlbum();
}
