package com.jikexueyuan.zixue.magic.deliciousfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * 作者：Magic on 2017/8/16 18:09
 * 邮箱：bonian1852@163.com
 */

public class DetailActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private ImageButton mBtnBack;

    private ImageView mImageFood;
    private TextView mTvName;
    private TextView mTvPrice;
    private TextView mTvDesc;
    private RatingBar mRatingBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        // TODO: 2017/8/31 获取Bundle
        Bundle bundle = getIntent().getExtras();
        loadView(parseFoodFromBundle(bundle));



    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mImageFood = (ImageView) findViewById(R.id.image_food);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mTvDesc = (TextView) findViewById(R.id.tv_description);
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);

    }

    // TODO: 2017/8/31 加载当前的数据
    private void loadView(Food food) {
        mImageFood.setImageResource(food.getImgResId());
        mTvTitle.setText(food.getName());
        mTvPrice.setText(String.format("价格:  %d", food.getPrice()));
        mTvName.setText(String.format("名称:  %s", food.getName()));
        mTvDesc.setText(food.getDescription());
        mRatingBar.setRating(food.getRating());
    }


    // TODO: 2017/8/31 解析Bundle传来的数据
    private Food parseFoodFromBundle(Bundle bundle){
        String name = bundle.getString("name");
        int imgResId = bundle.getInt("imgResId");
        String desc = bundle.getString("description");
        int price = bundle.getInt("price");
        int type = bundle.getInt("type");
        float rating = bundle.getFloat("rating");
        boolean isSpicy = bundle.getBoolean("isSpicy");
        return new Food(name,imgResId,price,type,isSpicy,rating,desc);
    }

    // TODO: 2017/8/31 返回键功能实现
    public void closeActivity(View view) {
        onBackPressed();
    }
}