package com.test.mytest.api;

import com.test.mytest.api.bean.CommentBean;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.api.response.BaseListRes;
import com.test.mytest.api.response.BaseRes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yyt19 on 2017/12/2.
 */

public interface ICommentApi {
    @GET("jokes/commentList")
    Observable<BaseListRes<CommentBean>> getPhotoDetailCommentList(@Query("jokesId") int photoId,
                                                                   @Query("userId") String userId,
                                                                   @Query("token") String token,
                                                                   @Query("pageNum") int pageNum,
                                                                   @Query("pageSize") int pageSize);

    @GET("jokes/comment")
    Observable<BaseRes> addComment(@Query("jokesId") String photoId,
                                   @Query("content") String content,
                                   @Query("defendantId") String defendantId,
                                   @Query("userId") String userId,
                                   @Query("token") String token);
}
