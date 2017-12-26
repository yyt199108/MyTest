package com.test.mytest.api.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2017-12-06.
 */

public class AccountBean implements Parcelable {
    public String petName;
    public String petType;
    public String petGender;
    public String petAge;
    public String location;

    /**
     * id : 802
     * phone : 18201125188
     * password :
     * openid : null
     * nickname : ffccc
     * avatar : https://youjiu.oss-cn-beijing.aliyuncs.com/pics/0563ed30-fbc0-4991-a1c0-5ccbe639a109
     * age : 55
     * gender : 其它
     * province : null
     * city : null
     * county : null
     * address : null
     * followNum : 5
     * fans : 3
     * level : {"id":1,"name":"新人","minScore":0,"maxScore":1999,"gmtCreated":1490580435000,"gmtUpdated":1490580439000,"medal":"http://app.dddrink.com/youjiu/app/medal/icons/lv1.png","level":1,"integral":0}
     * gmtCreate : 1494295118000
     * gmtModified : 1513672787000
     * status : 1
     * authStatus : null
     * token : 69DE65382103B5794C601FFF2DE7A634
     * thirdPlatQQUid : null
     * thirdPlatWechatUid : null
     * thirdPlatWeiboUid : null
     * deviceSystem : null
     * userId : 802
     * follow : false
     */

    public int id;
    public String phone;
    public String password;
    public String openid;
    public String nickname;
    public String avatar;
    public int age;
    public String gender;
    public String province;
    public String city;
    public String county;
    public String address;
    public int followNum;
    public int fans;
    public LevelBean level;
    public long gmtCreate;
    public long gmtModified;
    public int status;
    public String authStatus;
    public String token;
    public String thirdPlatQQUid;
    public String thirdPlatWechatUid;
    public String thirdPlatWeiboUid;
    public String deviceSystem;
    public int userId;
    public boolean follow;

    public static class LevelBean {
        /**
         * id : 1
         * name : 新人
         * minScore : 0
         * maxScore : 1999
         * gmtCreated : 1490580435000
         * gmtUpdated : 1490580439000
         * medal : http://app.dddrink.com/youjiu/app/medal/icons/lv1.png
         * level : 1
         * integral : 0
         */

        public int id;
        public String name;
        public int minScore;
        public int maxScore;
        public long gmtCreated;
        public long gmtUpdated;
        public String medal;
        public int level;
        public int integral;

    }

    public AccountBean() {

    }

    protected AccountBean(Parcel in) {
        petName=in.readString();
        petType=in.readString();
        petGender =in.readString();
        petAge =in.readString();
        location=in.readString();
    }

    public static final Creator<AccountBean> CREATOR = new Creator<AccountBean>() {
        @Override
        public AccountBean createFromParcel(Parcel in) {
            return new AccountBean(in);
        }

        @Override
        public AccountBean[] newArray(int size) {
            return new AccountBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(petName);
        dest.writeString(petType);
        dest.writeString(petGender);
        dest.writeString(petAge);
        dest.writeString(location);
    }
}
