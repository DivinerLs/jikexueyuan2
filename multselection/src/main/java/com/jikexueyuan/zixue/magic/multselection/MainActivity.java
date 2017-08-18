package com.jikexueyuan.zixue.magic.multselection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckBox_Samsung, mCheckBox_xiaomi, mCheckBox_huawei, mCheckBox_apple;
    private Button mSearch_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();
        //提供一些示例的数据,用于供我们筛选
        initData();
    }

    //需要来创建一个集合 来保存我们的示例数据
    private Set<Phone> mPhones = new HashSet<>();

    private void initData() {
        mPhones.add(new Phone("苹果", "iphone 7"));
        mPhones.add(new Phone("苹果", "iphone 6"));
        mPhones.add(new Phone("苹果", "iphone 6s"));
        mPhones.add(new Phone("苹果", "iphone 7 Plus"));

        mPhones.add(new Phone("三星","Galaxy S6"));
        mPhones.add(new Phone("三星","Galaxy S7"));
        mPhones.add(new Phone("三星","Galaxy S8 edge"));
        mPhones.add(new Phone("三星","Galaxy Note 5"));

        mPhones.add(new Phone("小米","小米5"));
        mPhones.add(new Phone("小米","小米4"));
        mPhones.add(new Phone("小米","小米3"));
        mPhones.add(new Phone("小米","小米 Note"));

        mPhones.add(new Phone("华为","荣耀8"));
        mPhones.add(new Phone("华为","P7"));
        mPhones.add(new Phone("华为","P8"));
        mPhones.add(new Phone("华为","荣耀8"));
    }

    private void init() {
        mCheckBox_apple = (CheckBox) findViewById(R.id.checkbox_apple);
        mCheckBox_huawei = (CheckBox) findViewById(R.id.checkbox_huawei);
        mCheckBox_Samsung = (CheckBox) findViewById(R.id.checkbox_samsung);
        mCheckBox_xiaomi = (CheckBox) findViewById(R.id.checkbox_xiaomi);

        mCheckBox_apple.setOnCheckedChangeListener(this);
        mCheckBox_Samsung.setOnCheckedChangeListener(this);
        mCheckBox_xiaomi.setOnCheckedChangeListener(this);
        mCheckBox_huawei.setOnCheckedChangeListener(this);

        mSearch_Button = (Button) findViewById(R.id.btn);
        mSearch_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //实现筛选过后的结果
                final Set<Phone> results = getFilteredPhones();

                for (Phone phone :results)
                {
                    System.out.println("phone"+phone.getBrand() + " " + phone.getModle());
                }
            }
        });
    }

    //实现筛选功能
    private Set<Integer> mCheckedBox_ResIds = new HashSet<>();

    //实现筛选过后的结果
    private Set<Phone> getFilteredPhones ()
    {
        Set<Phone> results = new HashSet<>();
        //如果里面没有元素的话 就代表我们没有设置筛选条件 直接返回 空的results
        if (mCheckedBox_ResIds.size() == 0) {
            return results;
        }

        //来遍历 所有的 示例数据
        for (Phone phone:mPhones)
        {
            String brand = phone.getBrand();

            int res_Id = -1;

            switch (brand){
                case "苹果":
                    res_Id = R.id.checkbox_apple;
                    break;

                case "三星":
                    res_Id = R.id.checkbox_samsung;
                    break;

                case "小米":
                    res_Id = R.id.checkbox_xiaomi;
                    break;

                case "华为":
                    res_Id = R.id.checkbox_huawei;
                    break;

                default:
                    res_Id = -1;
            }
            //判断这个里面是否包含这个 ID [mCheckedBox_ResIds.contains(res_Id);]
            if (mCheckedBox_ResIds.contains(res_Id))
            {
                results.add(phone);
            }

        }

        return results;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("MainActivity", "Checked" + "  " + isChecked + "---" + buttonView.getText());
        //下面的if操作 可以保证选择后的控件可以加入Set集合中  没有选中的可以剔除
        if (isChecked) {
            mCheckedBox_ResIds.add(buttonView.getId());
        } else {
            mCheckedBox_ResIds.remove(buttonView.getId());
        }
    }

}
