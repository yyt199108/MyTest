package com.test.mytest.adapter;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.ScreenUtils;
import com.blankj.utilcode.utils.SizeUtils;
import com.blankj.utilcode.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.QualityInfo;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;

import javax.annotation.Nullable;

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
    protected void convert(final BaseViewHolder helper, final PhotoInfoBean photoInfo) {
        final SimpleDraweeView simpleDraweeView = helper.getView(R.id.img_photo);
        ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        layoutParams.width = mWidth;
        layoutParams.height = (int) (mWidth * (photoInfo.height / photoInfo.width));
        simpleDraweeView.setLayoutParams(layoutParams);
        if (photoInfo != null && photoInfo.imageUrlList != null && photoInfo.imageUrlList.size() > 0) {
            simpleDraweeView.setImageURI(photoInfo.imageUrlList.get(0));
        }
    }
}
