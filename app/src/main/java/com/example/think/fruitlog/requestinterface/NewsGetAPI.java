package com.example.think.fruitlog.requestinterface;

import com.example.think.fruitlog.model.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsGetAPI {
    @Headers("Accept: application/json")
    @GET("?key=f283e0eda2d540f7454b804db2f795cb&num=30")
    Observable<News> getNews();
}
