package com.test.mytest.module.user.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.test.mytest.R;
import com.test.mytest.module.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by admin on 2017-12-05.
 */

public class PetMainPageActivity extends BaseActivity {
    private static final String TAG_PET_ID = "tagPetId";

    public static void startMainAct(Context context, String petId) {
        Intent intent = new Intent(context, PetMainPageActivity.class);
        intent.putExtra(TAG_PET_ID, petId);
        context.startActivity(intent);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_main_page;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        initTitle();
    }

    private void initTitle() {
        if (mTitle != null) {
            mTitle.setText("宠物详情");
        }
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @OnClick({R.id.back_img_lay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_img_lay:
                finish();
                break;
        }
    }
}
