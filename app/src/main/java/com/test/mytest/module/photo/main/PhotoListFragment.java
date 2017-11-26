package com.test.mytest.module.photo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.ImageUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jph.takephoto.model.TResult;
import com.test.mytest.R;
import com.test.mytest.api.bean.PhotoInfoBean;
import com.test.mytest.injector.components.DaggerPhotoListComponent;
import com.test.mytest.injector.module.PhotoListModule;
import com.test.mytest.module.base.BaseTakePhotoFragment;
import com.test.mytest.module.base.IBasePresenter;
import com.test.mytest.module.photo.detail.PhotoDetailActivity;
import com.test.mytest.utils.CustomTakePhotoHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.OnClick;

/**
 * Created by admin on 2017-11-22.
 */

public class PhotoListFragment extends BaseTakePhotoFragment<IBasePresenter> implements PhotoListContract.View {

    public static PhotoListFragment newInstance() {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    BaseQuickAdapter mAdapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photo_list;
    }

    @Override
    protected void initInjector() {
        DaggerPhotoListComponent.builder()
                .photoListModule(new PhotoListModule(this))
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void initViews() {
        if (mRecyView != null) {
            //初始化layoutManager
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            mRecyView.setLayoutManager(layoutManager);

            mRecyView.setAdapter(mAdapter);
            //设置adapter
            mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    page++;
                    mPresenter.getMoreData();
                }
            }, mRecyView);

            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    //跳转到详情
                    startActivity(new Intent(mContext, PhotoDetailActivity.class));
                }
            });
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {
        mPresenter.getData(isRefresh);
    }

    @Override
    public void loadData(List<PhotoInfoBean> data) {
        mAdapter.setNewData(data);
    }

    int page = 0;

    @Override
    public void loadMoreData(List<PhotoInfoBean> data) {
        mAdapter.addData(data);
        if (page >= 3) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void loadNoData() {

    }

    @OnClick({R.id.tv_add_pic})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.tv_add_pic:
                showAddPicDialog();
                break;
        }
    }

    private void showAddPicDialog() {
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

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        int[] widthHeight = CustomTakePhotoHelper.getImageWidthHeight(result.getImage().getCompressPath());
        ToastUtils.showLongToastSafe("width:" + widthHeight[0]
                + ";height:" + widthHeight[1]);
    }
}
