package com.test.mytest.api.bean;

import android.support.annotation.DrawableRes;

import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoInfoBean {



    /**
     * id : 609
     * content : 大人，你也太霸气了，666
     * userId : 802
     * thumbnailUrl : null
     * videoUrl : null
     * videoId : null
     * aliVideoUrl : null
     * gmtCreated : 2017-12-19 15:55:56
     * type : 2
     * imageUrl : https://youjiu.oss-cn-beijing.aliyuncs.com/pics/e6f4562f-b98b-4d91-b67b-3e7dee27e73d,
     * nickname : ffccc
     * avatar : https://youjiu.oss-cn-beijing.aliyuncs.com/pics/0563ed30-fbc0-4991-a1c0-5ccbe639a109
     * category : null
     * rcount : 0
     * gmtUpdated : null
     * status : 2
     * statusDesc : 审核通过
     * auditUserId : null
     * commentsNum : 8
     * likesNum : 3
     * remark : null
     * imageUrlList : ["https://youjiu.oss-cn-beijing.aliyuncs.com/pics/e6f4562f-b98b-4d91-b67b-3e7dee27e73d"]
     * like : true
     * collection : false
     */
    private Random random=new Random();
    public float height=random.nextInt(20)+50;
    public float width= (float) (Math.random()*100+50);
    @DrawableRes
    public int resId;
    public String infoName;
    public String photoTag;

    public int id;
    public String content;
    public int userId;
    public String thumbnailUrl;
    public String videoUrl;
    public String videoId;
    public String aliVideoUrl;
    public String gmtCreated;
    public String type;
    public String imageUrl;
    public String nickname;
    public String avatar;
    public String category;
    public int rcount;
    public String gmtUpdated;
    public String status;
    public String statusDesc;
    public String auditUserId;
    public int commentsNum;
    public int likesNum;
    public String remark;
    public boolean like;
    public boolean collection;
    public List<String> imageUrlList;

    //photoDetailBean
    public String photoUrl;
    public String petNick;
    public String petId;
    public String createTime;
    public String doFavor;
    public String detailContent;
    public String attentionCount;
}
