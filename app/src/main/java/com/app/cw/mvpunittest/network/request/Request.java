package com.app.cw.mvpunittest.network.request;

import com.app.cw.mvpunittest.network.response.Response;

import org.json.JSONObject;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Zaifeng on 2018/2/28.
 * 封装请求的接口
 */

public interface Request {

    public static String HOST = "http://www.wanandroid.com/";


    @POST("user/login")
    Observable<Response<JSONObject>> login(@Query("username") String userName,
                                           @Query("password") String password);

}
