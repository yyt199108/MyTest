package com.test.mytest.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.test.mytest.R;
import com.test.mytest.api.bean.CommentBean;

/**
 * Created by yyt19 on 2017/11/25.
 */

public class CommentListAdapter extends BaseQuickAdapter<CommentBean, BaseViewHolder> {
    private OnCommentClickListener mListener;

    public CommentListAdapter(OnCommentClickListener listener) {
        super(R.layout.adapter_comment_list);
        this.mListener = listener;
    }

    public interface OnCommentClickListener{
        void onClickDefendant(String defendantId,String defendtantNickName);

        void onClickComment(String commentUserId,String commentUserNickName);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CommentBean item) {
        ((TextView)helper.getView(R.id.tv_comment_content)).setMovementMethod(LinkMovementMethod.getInstance());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        SpannableString commentContentStr = new SpannableString(item.content);
        if(!TextUtils.isEmpty(item.defendantId)&&!TextUtils.isEmpty(item.defendantNickname)){
            ClickableSpan clickableSpan=new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if(mListener!=null){
                        mListener.onClickDefendant(item.defendantId,item.defendantNickname);
                    }
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.BLUE);
                    ds.setUnderlineText(false);
                }
            };
            if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
                SpannableString defendantNickStr=new SpannableString("@"+item.defendantNickname+" ");
                defendantNickStr.setSpan(clickableSpan,0,defendantNickStr.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.append(defendantNickStr);
            }else {
                spannableStringBuilder.append("@" + item.defendantNickname+" ", clickableSpan, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        ClickableSpan commentContentClickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if(mListener!=null){
                    mListener.onClickComment(item.userId,item.nickname);
                }
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.parseColor("#000000"));
            }
        };
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.LOLLIPOP){
            SpannableString commentContentSpan=new SpannableString(commentContentStr);
            commentContentSpan.setSpan(commentContentClickableSpan,0,commentContentSpan.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.append(commentContentSpan);
        }else {
            spannableStringBuilder.append(commentContentStr, commentContentClickableSpan, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        helper.setText(R.id.tv_pet_nick, item.nickname)
                .setText(R.id.tv_comment_content, spannableStringBuilder)
                .setText(R.id.tv_comment_time, item.createTime)
                .setText(R.id.tv_comment_location, item.commentLocation)
                .setText(R.id.tv_favor_count, item.commentFavorCount + "");
        if (item.showCommentTypeTag) {
            helper.setText(R.id.comment_type_tag, TextUtils.isEmpty(item.commentTypeName) ? "热门评论" : item.commentTypeName);
        }
        helper.getView(R.id.comment_type_tag).setVisibility(item.showCommentTypeTag ? View.VISIBLE : View.GONE);
        if (!TextUtils.isEmpty(item.avatar)) {
            ((SimpleDraweeView) helper.getView(R.id.sdv_avatar)).setImageURI(item.avatar);
        }
        helper.addOnClickListener(R.id.tv_pet_nick);
        helper.addOnClickListener(R.id.sdv_avatar);
    }
}
