package com.jikexueyuan.zixue.magic.deliciousfood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


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
    private Button mBtnDetail;

    // TODO: 2017/8/18 第六是 两个翻页的按钮 上一页和下一页
    private Button mBtnPrev;
    private Button mBtnNext;

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

        mBtnDetail = (Button) findViewById(R.id.btn_detail);
        // TODO: 2017/8/16  Paint paint = mBtnDetail.getPaint(); 获取画笔对象
        Paint paint = mBtnDetail.getPaint();
        // TODO: 2017/8/16  两个参数 1[保留我们现在的flag]-2[带有下划线的flag]
        paint.setFlags(paint.getFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }

    // TODO: 2017/8/18 来选中所有的 CheckBox
    private void checkAll() {
        int checkBoxCount = mCheckBoxContainer.getChildCount();
        for (int i = 0; i < checkBoxCount; i++) {
            View checkBox = mCheckBoxContainer.getChildAt(i);
            if (checkBox instanceof CheckBox) {
                ((CheckBox) checkBox).setChecked(true);
            }
        }

    }
}
