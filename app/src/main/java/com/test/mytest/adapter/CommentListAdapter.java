package com.test.mytest.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.mytest.R;
import com.test.mytest.api.bean.CommentBean;

/**
 * Created by yyt19 on 2017/11/25.
 */

public class CommentListAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    public CommentListAdapter() {
        super(R.layout.adapter_comment_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean item) {
        helper.setText(R.id.tv_pet_nick, item.nickName)
                .setText(R.id.tv_comment_content, item.commentContent)
                .setText(R.id.tv_comment_time, item.createTime)
                .setText(R.id.tv_comment_location, item.commentLocation);
        if (item.showCommentTypeTag) {
            helper.setText(R.id.comment_type_tag, TextUtils.isEmpty(item.commentTypeName) ? "热门评论" : item.commentTypeName);
        }
        helper.getView(R.id.comment_type_tag).setVisibility(item.showCommentTypeTag ? View.VISIBLE : View.GONE);
    }
}
