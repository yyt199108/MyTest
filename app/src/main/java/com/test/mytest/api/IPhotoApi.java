package com.test.mytest.api;

import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseBeanRes;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.api.response.BaseRes;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by yyt19 on 2017/11/22.
 */

public interface IPhotoApi {
    @GET("jokes/list")
    Observable<BaseListRes<PhotoInfoBean>> getMainPetPhotoList(@Query("pageNum") int pageNum,
                                                               @Query("pageSize") int pageSize,
                                                               @Query("userId") String userId,
                                                               @Query("token") String token,
                                                               @Query("type") String type);

    @GET("jokes/details")
    Observable<BaseBeanRes<PhotoInfoBean>> getDetailPetPhoto(@Query("userId") String userId,
                                                             @Query("token") String token,
                                                             @Query("id") int id);
    @GET("like")
    Observable<BaseRes> like(@QueryMap Map<String, String> map);
}
