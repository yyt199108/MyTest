package com.test.mytest.widget;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.utils.KeyboardUtils;
import com.test.mytest.R;

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

    public static void showShareDialog(final Context context){
        BottomSheetDialog addCommentDialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_share, null);
        RecyclerView recyclerView=view.findViewById(R.id.recy_view);

        addCommentDialog.setContentView(view);
        addCommentDialog.show();
    }
}
