package com.test.mytest.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.blankj.utilcode.utils.KeyboardUtils;
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
        BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_comment, null);
        final EditText etApplyComment = view.findViewById(R.id.et_apply_comment);
        view.findViewById(R.id.tv_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lisetener != null) {
                    lisetener.addComment(etApplyComment.getText().toString());
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

    public static void showShareDialog(final Context context) {
//        BottomSheetDialog addCommentDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogStyle);
        BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
//        addCommentDialog.getWindow().findViewById(R.id.design_botttom_sheet)
//                .setBackgroundResource(android.R.color.transparent);
//        addCommentDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//
//        addCommentDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
//                .setBackgroundColor(mActivity.getResources().getColor(R.color.transparent));
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_share, null);

        RecyclerView recyclerView = view.findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        List<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "微信朋友圈", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "发送给朋友", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "微博", 1));
        ShareAdapter adapter = new ShareAdapter(imageBeans);
        recyclerView.setAdapter(adapter);
        addCommentDialog.setContentView(view);
        addCommentDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet)
                .setBackgroundResource(android.R.color.transparent);
        addCommentDialog.show();
    }

    public static void showRewardDialog(final Context context) {
        BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
//        View view = LayoutInflater.from(context)
//                .inflate(R.layout.dialog_reward, null);


        addCommentDialog.setContentView(R.layout.dialog_reward);

        RecyclerView recyclerView = addCommentDialog.findViewById(R.id.recy_view);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.HORIZONTAL, false));

        List<ImageBean> imageBeans = new ArrayList<>();
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "1元", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "6元", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "10元", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "20元", 1));
        imageBeans.add(new ImageBean(R.drawable.ic_pet1, "100元", 1));
        RewardAdapter adapter = new RewardAdapter(imageBeans);
        recyclerView.setAdapter(adapter);
        addCommentDialog.show();
    }
}
