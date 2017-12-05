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

public class RewardAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public RewardAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_reward_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_share_name, item);
    }
}
