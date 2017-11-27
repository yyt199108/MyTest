package com.test.mytest.api.response;

import java.util.List;

/**
 * Created by admin on 2017-11-27.
 */

public class BaseListRes<T> extends BaseRes {
    public DataBean<T> data;

    public class DataBean<T> {
        public List<T> dataList;
        public PageVo pageVo;
    }
}
