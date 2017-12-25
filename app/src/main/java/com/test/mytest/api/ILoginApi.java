package com.test.mytest.api;

import com.test.mytest.api.bean.UserBean;
import com.test.mytest.api.response.BaseBeanRes;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by admin on 2017-12-25.
 */

public interface ILoginApi {
    @GET("user/login")
    Observable<BaseBeanRes<UserBean>> userLogin(@QueryMap Map<String, String> map);
}
