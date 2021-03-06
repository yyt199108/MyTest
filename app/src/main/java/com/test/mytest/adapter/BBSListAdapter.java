package com.test.mytest.adapter;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;

/**
 * Created by admin on 2017-11-22.
 */

public class BBSListAdapter extends BaseQuickAdapter<PhotoInfoBean, BaseViewHolder> {


    //图片宽度
    private int mWidth;

    public BBSListAdapter() {
        super(R.layout.adapter_bbs_main_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoInfoBean photoInfo) {
        ((ImageView) (helper.getView(R.id.img_photo))).setImageResource(photoInfo.resId);
        helper.setText(R.id.tv_photo_info, TextUtils.isEmpty(photoInfo.infoName)?"":photoInfo.infoName);
    }
}
