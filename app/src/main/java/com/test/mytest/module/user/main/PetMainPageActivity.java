package com.test.mytest.module.user.main;

import android.content.Context;
import android.content.Intent;

import com.test.mytest.R;
import com.test.mytest.module.base.BaseActivity;

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

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

}
