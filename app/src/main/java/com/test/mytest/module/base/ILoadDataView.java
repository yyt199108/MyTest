package com.test.mytest.module.base;

/**
 * Created by yyt19 on 2017/11/22.
 */

public interface ILoadDataView<T> extends IBaseView {
    /**
     * 加载数据
     * @param data
     */
    void loadData(T data);

    /**
     * 加载更多
     * @param data
     */
    void loadMoreData(T data);

    /**
     * 没有数据
     */
    void loadNoData();

    void hasNoMoreData();
}
