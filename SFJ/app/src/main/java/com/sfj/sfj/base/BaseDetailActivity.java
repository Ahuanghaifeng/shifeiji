package com.sfj.sfj.base;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sfj.sfj.R;
import com.sfj.sfj.widget.AppToolbar;


/**
 * Created by 黄海峰 on 2018/5/15.
 */

public class BaseDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    protected boolean initTitle(AppToolbar appBar,String title) {
        appBar.showBottomLine(true);
        TextView tvTitle = appBar.creatCenterView(TextView.class);
        tvTitle.setText(title);
        tvTitle.setTextColor(Color.parseColor("#000000"));
//        ImageView leftView = appBar.creatLeftView(ImageView.class, new int[]{16, 0, 0, 0}, null);
        Drawable drawable= getResources().getDrawable(R.mipmap.left_arrow_back);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        TextView leftTextView = appBar.creatLeftView(TextView.class);
        leftTextView.setCompoundDrawables(drawable, null, null, null);
        leftTextView.setCompoundDrawablePadding(10);
        leftTextView.setText("返回");
        leftTextView.setTextColor(Color.parseColor("#000000"));
//        leftView.setImageResource(R.drawable.left_arrow_back);
        leftTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        appBar.build(false);
        return true;
    }


    protected void initView(){

    }
}
