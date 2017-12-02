package com.example.adminjs.zhouxi;

import com.example.adminjs.zhouxi.bean.Bean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Adminjs on 2017/12/2.
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
public interface IGetDataBase {
    @GET("/toutiao/index")
    Call<Bean>get(@Query("key") String key);

//    @FormUrlEncoded
//    @POST("/toutiao/index")
//    Call<Bean>post(@Field("key") String key);
}
