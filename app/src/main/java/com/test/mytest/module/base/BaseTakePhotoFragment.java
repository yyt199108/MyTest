package com.test.mytest.module.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.socks.library.KLog;
import com.test.mytest.R;
import com.test.mytest.utils.CustomTakePhotoHelper;

/**
 * Created by yyt19 on 2017/11/26.
 */

public abstract class BaseTakePhotoFragment<T extends IBasePresenter> extends BaseFragment
        implements TakePhoto.TakeResultListener, InvokeListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(getActivity(), type, invokeParam, this);
    }

    /**
     * 获取TakePhoto实例
     *
     * @return
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult result) {
        KLog.i("takeSuccess：" + result.getImage().getCompressPath());
        int[] widthHeight = CustomTakePhotoHelper.getImageWidthHeight(result.getImage().getCompressPath());
        ToastUtils.showLongToastSafe("width:" + widthHeight[0]
                + ";height:" + widthHeight[1]);
    }

    @Override
    public void takeFail(TResult result, String msg) {
        KLog.i("takeFail:" + msg);
    }

    @Override
    public void takeCancel() {
        KLog.i(getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    protected void showAddPicDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);
        View dialogView = LayoutInflater.from(mContext)
                .inflate(R.layout.dialog_take_photo, null);
        TextView tvTakePhoto = (TextView) dialogView.findViewById(R.id.tv_take_photo);
        TextView tvPhotoAlbum = (TextView) dialogView.findViewById(R.id.tv_photo_album);
        TextView tvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        tvTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTakePhotoHelper.pickByCamera(getTakePhoto());
                dialog.dismiss();
            }
        });
        tvPhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTakePhotoHelper.pickByAlbum(getTakePhoto());
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setContentView(dialogView);
        dialog.show();
    }
}
