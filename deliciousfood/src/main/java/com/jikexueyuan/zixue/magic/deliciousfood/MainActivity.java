package com.jikexueyuan.zixue.magic.deliciousfood;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO: 2017/8/31 作业1:添加一个引导页 

    // TODO: 2017/8/31 添加俩个状态常量 用于搜索与重置 还有一个当前Activity的状态 一开始是正常的状态
    private static final int STATE_FILTER = 1; //搜索的状态
    private static final int STATE_NORMAL = 2; //正常的状态
    private int mState = STATE_NORMAL;

    private static final String TAG = "MainActivity";
    // TODO: 2017/8/18 需要把界面上的控件都声明出来
    // TODO: 2017/8/18  第一是显示CheckBox的区域
    private LinearLayout mCheckBoxContainer;
    private CheckBox mCheckBoxChineseFood;
    private CheckBox mCheckBoxFastFood;
    private CheckBox mCheckBoxDessertFood;

    // TODO: 2017/8/18 第二是 RadioGroup 的区域
    private RadioGroup mRadioGroup;
    private RadioButton mRadioYes;
    private RadioButton mRadioNo;

    // TODO: 2017/8/18 第三是表示价格的TextView和SeekBar
    private TextView mTvPrice;
    private SeekBar mSeekBarPrice;

    // TODO: 2017/8/18 第四是两个按钮 一个重置 一个搜索
    private Button mBtnReset;
    private Button mBtnSearch;

    // TODO: 2017/8/18 第五是 显示美食的图片 以及 跳转到详情页的按钮
    private ImageView mImageFood;
    private Button mBtnDetail; //详情

    // TODO: 2017/8/18 第六是 两个翻页的按钮 上一页和下一页
    private Button mBtnPrev;
    private Button mBtnNext;

    private List<Food> mFoodList;//美食列表

    private List<Food> mFilteredList;//过滤后的美食列表

    // TODO: 2017/8/31 当前页的变量
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        // TODO: 2017/8/18  在MainActivity中调用FoodAPI
        List<Food> foodList = FoodAPI.getDemoFood(this);
    }

    // TODO: 2017/8/16 给点击查看详情 设置下划线
    private void init() {
        // TODO: 2017/8/18 CheckBox初始化 
        mCheckBoxContainer = (LinearLayout) findViewById(R.id.checkbox_container);
        mCheckBoxChineseFood = (CheckBox) findViewById(R.id.checkbox_chinesefood);
        mCheckBoxFastFood = (CheckBox) findViewById(R.id.checkbox_fastfood);
        mCheckBoxDessertFood = (CheckBox) findViewById(R.id.checkbox_dessert);
        checkAll();// TODO: 2017/8/18 写一个方法checkAll();来选中所有的 CheckBox

        // TODO: 2017/8/18  RadioGroup和RadioButton初始化
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);
        mRadioYes = (RadioButton) findViewById(R.id.radio_yes);
        mRadioNo = (RadioButton) findViewById(R.id.radio_no);
        // TODO: 2017/8/31 我们需要选中一个默认的RadioButton
        // mRadioGroup.check(R.id.radio_no);

        mBtnDetail = (Button) findViewById(R.id.btn_detail);
        // TODO: 2017/8/16  Paint paint = mBtnDetail.getPaint(); 获取画笔对象
        Paint paint = mBtnDetail.getPaint();
        // TODO: 2017/8/16  两个参数 1[保留我们现在的flag]-2[带有下划线的flag]
        paint.setFlags(paint.getFlags()|Paint.UNDERLINE_TEXT_FLAG);

        mTvPrice = (TextView) findViewById(R.id.tv_price);//价格
        mSeekBarPrice = (SeekBar) findViewById(R.id.seekbar_price);
        // TODO: 2017/8/31 先把SeekBar设置最大值
        // mSeekBarPrice.setMax(100);
        // TODO: 2017/8/31 把价格也设置成最大的
        // mTvPrice.setText(" "+mSeekBarPrice.getProgress());
        // TODO: 2017/8/31 给mSeekBarPrice设置监听事件
        mSeekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO: 2017/8/31 更新价格
                mTvPrice.setText(" "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // TODO: 2017/8/31 初始化数据后才能为 mImageFood 设置图片 所以要声明出一个List<Food>来保存所有的Food的数据
        mImageFood = (ImageView) findViewById(R.id.image_food);//详情页图片
        mFoodList = initData();
        //mCurrentPage = 0;
        //mImageFood.setImageResource(mFoodList.get(mCurrentPage).getImgResId());

        // TODO: 2017/8/31 翻页按钮初始化 上一页 和 下一页
        mBtnPrev = (Button) findViewById(R.id.btn_prev);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        // TODO: 2017/8/31 搜索与重置 并设置监听事件
        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnSearch.setOnClickListener(this);
        mBtnReset.setOnClickListener(this);

        // TODO: 2017/8/31 要把 loadInitialView(); 放到init();的最下方 不然会报错
        // TODO: 2017/8/31 应该是先初始化 翻页按钮 再调用  loadInitialView();
        loadInitialView();


    }

    private List<Food> initData(){
        return FoodAPI.getDemoFood(this);
    }


    /**
     * 来选中mCheckBoxContainer中所有的checkBox
     */
    // TODO: 2017/8/18 来选中所有的 CheckBox
    private void checkAll() {
        // TODO: 2017/8/31  mCheckBoxContainer.getChildCount() 获得CheckBox子控件的数量
        int checkBoxCount = mCheckBoxContainer.getChildCount();
        for (int i = 0; i < checkBoxCount; i++) {
            View checkBox = mCheckBoxContainer.getChildAt(i);
            if (checkBox instanceof CheckBox) {
                ((CheckBox) checkBox).setChecked(true);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_prev:
                showPrevPage();
                break;

            case R.id.btn_next:
                showNextPage();
                break;

            case R.id.btn_reset:
                mState = STATE_NORMAL;
                loadInitialView();//加载初始视图
                break;

            case R.id.btn_search:
                mState = STATE_FILTER;
                showFilterFoods();//显示搜索的食物
                break;

            default:
                break;
        }
    }

    // TODO: 2017/8/31  重置按钮的具体实现
    private void loadInitialView() {
        mBtnPrev.setEnabled(true);
        mBtnNext.setEnabled(true);
        mBtnDetail.setEnabled(true);
        checkAll();
        mSeekBarPrice.setMax(100);
        mRadioGroup.check(R.id.radio_no);
        showPageAtIndex(0);//显示第一张图片

    }

    // TODO: 2017/8/31 搜索按钮的具体实现
    private void showFilterFoods() {
        mFilteredList =  filteredFoods();
        // TODO: 2017/8/31 过滤之后的可能是一个空的列表 如果是空的话 我们需要把翻页按钮给禁用掉 并且显示一张空的图片 以及详情按钮也给禁用掉 如果不是空的 我们才按照正常的流程
        boolean enablePageButton =(mFilteredList.size() > 1);
        mBtnNext.setEnabled(enablePageButton);
        mBtnPrev.setEnabled(enablePageButton);
        mBtnDetail.setEnabled(!mFilteredList.isEmpty());
        if (mFilteredList.isEmpty()){
            mImageFood.setImageResource(R.drawable.nodata);
        }else {
            showPageAtIndex(0);
        }
    }

    private List<Food> filteredFoods() {
        // TODO: 2017/8/31 1.通过价格 来搜索
        int maxPrice = mSeekBarPrice.getProgress();
        // TODO: 2017/8/31 2.通过是否要辣 来搜索 R.id.radio_no 就是要辣的状态
        boolean isSpicy = (mRadioGroup.getCheckedRadioButtonId() == R.id.radio_no);
        // TODO: 2017/8/31 3.通过美食类型 来搜索
        List<Integer> selectedFoodTypes = new ArrayList<>();
        //通过判断这三个CheckBox是否被选中
        if (mCheckBoxChineseFood.isChecked()){
            selectedFoodTypes.add(Food.CHINESE_FOOD);
        }
        if (mCheckBoxFastFood.isChecked()){
            selectedFoodTypes.add(Food.FAST_FOOD);
        }
        if (mCheckBoxDessertFood.isChecked()){
            selectedFoodTypes.add(Food.DESSERT_FOOD);
        }
        // 首先要过滤FoodList这个列表 遍历的结果需要放到List里 results
        List<Food> results = new ArrayList<>();
        for (Food food :mFoodList){
            //food的价格是否小于maxPrice 并且 是否选中了类型 [contains:包含]
            if (food.getPrice()<maxPrice && selectedFoodTypes.contains(food.getType())){
                //要辣或者不辣
                if (isSpicy || !food.isSpicy()){
                    results.add(food);
                }
            }
        }
        Log.d(TAG, "showFilterFoods: "+ results);
        return results;
    }

    //currentShowingFoods() 显示当前的食物
    private List<Food> currentShowingFoods(){
        switch (mState){
            case STATE_FILTER:
                return mFilteredList;
            case STATE_NORMAL:
                return mFoodList;
            default:
                return mFoodList;
        }
    }

    // TODO: 2017/8/31 翻页功能 上一页 showPrevPage() 的实现
    private void showPrevPage() {
        //因为直接 -1 可能会减到0 所以我们做处理 + 上当前mFoodList的大小 这样的话 我们会小于0了
        //然后再对FoodList进行取余 这样的话就会在FoodList长度的范围内了
        int PrevPage = (mCurrentPage -1 + currentShowingFoods().size()) % currentShowingFoods().size();
        showPageAtIndex(PrevPage);
    }

    // TODO: 2017/8/31 翻页功能 下一页 showNextPage() 的实现
    private void showNextPage() {
        //下一页 因为有可能会超出总数 所以我们要进行一个取余 这样的话 无论我们怎么 +1 都会小于 mFoodList的长度
        int nextPage = (mCurrentPage + 1) % currentShowingFoods().size();
        showPageAtIndex(nextPage);
    }

    // TODO: 2017/8/31 showPageAtIndex 显示页面索引
    private void showPageAtIndex(int index){
        Log.d(TAG, "showPageAtIndex: "+ index);
        Food food = currentShowingFoods().get(index);
        mImageFood.setImageResource(food.getImgResId());
        mCurrentPage = index;

    }

    // TODO: 2017/8/31 跳转到详情页
    public void openDetailActivity(View view) {
        Intent intent = new Intent(this,DetailActivity.class);
        Bundle bundle = new Bundle();
        Food food = currentShowingFoods().get(mCurrentPage);
        bundle.putString("name",food.getName());//名字
        bundle.putInt("imgResId",food.getImgResId());//图片的ID
        bundle.putFloat("rating",food.getRating());//评分
        bundle.putInt("price",food.getPrice());//价格
        bundle.putString("description",food.getDescription());//简介
        bundle.putBoolean("isSpicy",food.isSpicy());//是否要辣
        bundle.putInt("type",food.getType());//类型
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
