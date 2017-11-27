package com.test.mytest.api.response;

/**
 * Created by admin on 2017-11-27.
 */

public class PageVo {

    public int pageNo;
    public int pageSize;
    public int totalCount;
    public int totalPage;


    /**
     * 是否有下一页
     *
     * @param pageNo
     * @return
     */
    public boolean hasNextPage(int pageNo) {
        if (pageNo < totalPage) {
            return true;
        }
        return false;
    }
}
