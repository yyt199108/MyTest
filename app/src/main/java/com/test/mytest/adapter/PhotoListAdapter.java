package com.test.mytest.adapter;

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

public class PhotoListAdapter extends BaseQuickAdapter<PhotoInfoBean, BaseViewHolder> {


    //图片宽度
    private int mWidth;

    public PhotoListAdapter() {
        super(R.layout.adapter_photo_list);

        mWidth = ScreenUtils.getScreenWidth() / 2 - SizeUtils.dp2px(Utils.getContext().getResources().getDimensionPixelOffset(R.dimen.photo_margin_width));
    }

    @Override
    protected void convert(BaseViewHolder helper, PhotoInfoBean photoInfo) {
        ViewGroup.LayoutParams layoutParams = helper.getView(R.id.img_photo).getLayoutParams();
        layoutParams.width = mWidth;
        layoutParams.height = (int) (mWidth * (photoInfo.height / photoInfo.width));
        helper.getView(R.id.img_photo).setLayoutParams(layoutParams);
        ((ImageView) (helper.getView(R.id.img_photo))).setImageResource(photoInfo.resId);
    }
}
