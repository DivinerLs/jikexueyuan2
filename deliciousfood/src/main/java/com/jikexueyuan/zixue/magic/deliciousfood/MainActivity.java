package com.jikexueyuan.zixue.magic.deliciousfood;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    // TODO: 2017/8/16 给点击查看详情 设置下划线
    private void init() {
        mBtnDetail = (Button) findViewById(R.id.btn_detail);
        // TODO: 2017/8/16  Paint paint = mBtnDetail.getPaint(); 获取画笔对象
        Paint paint = mBtnDetail.getPaint();
        // TODO: 2017/8/16  两个参数 1[保留我们现在的flag]-2[带有下划线的flag]
        paint.setFlags(paint.getFlags()|Paint.UNDERLINE_TEXT_FLAG);
    }
}
