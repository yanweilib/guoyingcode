package com.example.androidcode.Widgets.SmoothImageView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidcode.Activity.BaseActivity;
import com.example.androidcode.Units.MyBitmapUtils;


/**
 * Created by YSB on 2016/12/7.
 */
public class SpaceImageDetailActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

    }

    private void initView()
    {
        String mDatas =  getIntent().getStringExtra("images");
        int mPosition = getIntent().getIntExtra("position", 0);
        int mLocationX = getIntent().getIntExtra("locationX", 0);
        int mLocationY = getIntent().getIntExtra("locationY", 0);
        int  mWidth = getIntent().getIntExtra("width", 0);
        int mHeight = getIntent().getIntExtra("height", 0);

        SmoothImageView  imageView = new SmoothImageView(this);
        imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
        imageView.transformIn();
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        setContentView(imageView);
        MyBitmapUtils.instance(this).display(imageView,mDatas );

        imageView.setClickable(true);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
