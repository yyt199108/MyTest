package com.test.mytest.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.test.mytest.R;
import com.test.mytest.api.bean.ImageBean;

import java.util.List;

/**
 * Created by admin on 2017-12-05.
 */

public class ShareAdapter extends BaseQuickAdapter<ImageBean,BaseViewHolder> {

    public ShareAdapter(@Nullable List<ImageBean> data) {
        super(R.layout.adapter_share_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageBean item) {
        ((SimpleDraweeView)helper.getView(R.id.sdv_share_image)).setActualImageResource(item.imageResId);
        helper.setText(R.id.tv_share_name, item.imageDescribe);
    }
}
