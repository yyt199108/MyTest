package com.test.mytest.api.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yyt19 on 2017/11/25.
 */

public class CommentBean {
    /**
     * userId : 1226
     * avatar : https://tvax4.sinaimg.cn/crop.0.25.1242.1242.180/006julSply8fdm4hmadndj30yi0zx414.jpg
     * nickname : 老冷带你疯狂
     * defendantId : null
     * defendantNickname : null
     * gmtCreate : 2017-12-21 11:06:13
     * content : 啥玩意儿？二哈，还是狼？嗷呜。。。
     * commentId : 557
     */
    public String userId;
    public String nickname;
    @SerializedName(value = "gmtCreate")
    public String createTime;
    public String content;
    public int commentId;
    public String commentLocation;
    public int commentFavorCount;
    public String avatar;
    public String doFavor;
    public boolean showCommentTypeTag;
    public String commentTypeName; //热门评论
    public String defendantId;
    public String defendantNickname;
}
