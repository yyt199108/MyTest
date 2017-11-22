package com.test.mytest.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoListAdapter extends BaseQuickAdapter<PhotoInfoBean, BaseViewHolder> {


    public PhotoListAdapter() {
        super(R.layout.adapter_photo_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoInfoBean photoInfo) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        layoutParams.mAspectRatio=(photoInfo.height / photoInfo.width);
        layoutParams.height = (int) (helper.getView(R.id.img_photo).getMeasuredWidth() * (photoInfo.height / photoInfo.width));
        helper.getView(R.id.img_photo).setLayoutParams(layoutParams);
        ((ImageView) (helper.getView(R.id.img_photo))).setImageResource(photoInfo.resId);
    }
}
