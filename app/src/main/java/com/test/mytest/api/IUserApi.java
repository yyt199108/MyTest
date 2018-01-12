package com.test.mytest.api;

import com.test.mytest.api.bean.AccountBean;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.api.response.BaseRes;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by admin on 2017-12-25.
 */

public interface IUserApi {
    @GET("user/login")
    Observable<BaseBeanRes<AccountBean>> userLogin(@QueryMap Map<String, String> map);

    @GET("user/get")
    Observable<BaseBeanRes<AccountBean>> getUserInfo( @Query("userId") String userId,
                                     @Query("token") String token,
                                     @Query("id") String id);
}
