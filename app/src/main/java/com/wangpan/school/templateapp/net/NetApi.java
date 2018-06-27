package com.wangpan.school.templateapp.net;

import com.wangpan.school.templateapp.bean.User;
import com.wangpan.school.templateapp.net.response.HttpResult;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface NetApi {

    @GET("girls")
    Observable<HttpResult<List<User>>> getGirls2();

    @FormUrlEncoded
    @POST("addGirl")
    Observable<HttpResult<User>> addGirl(@Field("cupSize") String cupSize, @Field("age") int age);
}
