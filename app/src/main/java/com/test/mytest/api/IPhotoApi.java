package com.test.mytest.api;

import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yyt19 on 2017/11/22.
 */

public interface IPhotoApi {
    @GET("http://www.baidu.com")
    Observable<BaseListRes<PhotoInfoBean>> getMainPetPhotoList();
}
