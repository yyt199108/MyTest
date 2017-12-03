package com.test.mytest.api;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by yyt19 on 2017/12/2.
 */

public interface ICommentApi {
    @GET("http://123.207.141.79:8081/xlqhoutlook/api/appVersion/checkUpdate")
    Observable<BaseListRes<CommentBean>> getPhotoDetailCommentList();
}
