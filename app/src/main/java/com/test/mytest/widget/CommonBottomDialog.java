package com.test.mytest.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.utils.KeyboardUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.test.mytest.R;
import com.test.mytest.adapter.RewardAdapter;
import com.test.mytest.adapter.ShareAdapter;
import com.test.mytest.api.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by admin on 2017-12-04.
 */

public class CommonBottomDialog {
    public interface AddCommentLisetener {
        void addComment(String commentContent);
    }

    public static void showAddCommentDialog(final Context context, final AddCommentLisetener lisetener) {
        final BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_comment, null);
        final EditText etApplyComment = view.findViewById(R.id.et_apply_comment);
        view.findViewById(R.id.tv_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisetener != null) {
                    lisetener.addComment(etApplyComment.getText().toString());
                }
                addCommentDialog.dismiss();
            }
        });
        etApplyComment.setFocusable(true);
        etApplyComment.setFocusableInTouchMode(true);
        etApplyComment.requestFocus();

        Observable.timer(110, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        KeyboardUtils.showSoftInput(etApplyComment);
                    }
                });

        addCommentDialog.setContentView(view);
        addCommentDialog.show();
    }

    public static void showShareDialog(final Context context) {
        final BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_share, null);

        RecyclerView recyclerView = view.findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        final List<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(new ImageBean(R.drawable.pengyouquan, "微信朋友圈", 1));
        imageBeans.add(new ImageBean(R.drawable.weixin, "发送给朋友", 1));
        imageBeans.add(new ImageBean(R.drawable.weibo, "微博", 1));
        ShareAdapter adapter = new ShareAdapter(imageBeans);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLongToastSafe("分享" + imageBeans.get(position).imageDescribe);
                addCommentDialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCommentDialog.dismiss();
            }
        });
        addCommentDialog.setContentView(view);
        addCommentDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);
        addCommentDialog.show();
    }

    public static void showRewardDialog(final Context context) {
        final BottomSheetDialog rewardDialog = new BottomSheetDialog(context);
        rewardDialog.setContentView(R.layout.dialog_reward);
        rewardDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);

        RecyclerView recyclerView = rewardDialog.findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false));

        final List<String> stringList = new ArrayList<>();
        stringList.add("1元");
        stringList.add("6元");
        stringList.add("8元");
        stringList.add("50元");
        stringList.add("100元");
        stringList.add("500元");
        RewardAdapter adapter = new RewardAdapter(stringList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLongToastSafe("打赏" + stringList.get(position));
                rewardDialog.dismiss();
            }
        });
        rewardDialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rewardDialog.dismiss();
            }
        });
        rewardDialog.show();
    }

    public interface EditLisetener {
        void editComplete(String editTextString, int type);
    }

    public static void showEditTextDialog(final Context context, final int type, String hintText, String titleText, final EditLisetener lisetener) {
        final BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_edit, null);
        ((TextView) view.findViewById(R.id.tv_title)).setText(titleText);
        final EditText etApplyComment = view.findViewById(R.id.et_editshow);
        etApplyComment.setHint(hintText);
        view.findViewById(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etApplyComment.getText().toString().trim().length() > 0) {
                    if (lisetener != null) {
                        lisetener.editComplete(etApplyComment.getText().toString(), type);
                    }

                    addCommentDialog.dismiss();
                } else {
                    ToastUtils.showLongToastSafe("不能为空");
                }
            }
        });
        etApplyComment.setFocusable(true);
        etApplyComment.setFocusableInTouchMode(true);
        etApplyComment.requestFocus();

        Observable.timer(110, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        KeyboardUtils.showSoftInput(etApplyComment);
                    }
                });

        addCommentDialog.setContentView(view);
        addCommentDialog.show();
    }
}
