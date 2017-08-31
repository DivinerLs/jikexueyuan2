package com.jikexueyuan.zixue.magic.dialog_box_collection;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // TODO: 2017/8/27  确定取消对话框
    public void click01(View view) {
        /*
            在手机里面很多地方用到对话框,所以谷歌工程师创建了一种设计模式叫做[工厂模式]
            [工厂模式]:就是把模板定义好了,告诉'工厂'我需要什么什么样式的模式.
            工厂模式 首先需要一个工厂
         */
        //1.建立一个'工厂'
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //告诉'工厂'需要什么样的产品
        //2.1 设置标题
        builder.setTitle("友情提示!");
        //2.2 设置文本
        builder.setMessage("若练此功,必先自宫,是否继续?");
        //2.3 确定键
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "啊~~~~", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "即使自宫,也未必成功!", Toast.LENGTH_LONG).show();
            }
        });

        //2.4 取消键
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "若不自宫,一定不成功!", Toast.LENGTH_SHORT).show();
            }
        });

        //3 创建对话框 [必需有,不然对话框不会创建,还一定要show出来 和 Toast 一样]
        builder.create().show();
    }

    // TODO: 2017/8/27 单选对话框
    public void click02(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择性别:");
        final String[] item = {"男","女","非男非女"};
        //3个参数:[1.String类型的数组] [2.默认哪个条目选中 0男 1女 2非男非女] [OnClickListener]
        builder.setSingleChoiceItems(item, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //关闭对话框
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "您的性别是"+item[which], Toast.LENGTH_SHORT).show();
            }
        });

        // TODO: 2017/8/27  builder.create().show(); ==  builder.show();
        builder.show();
    }

    // TODO: 2017/8/27 多选对话框
    public void click03(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请您选择喜欢的水果:");
        //3个参数:[1 String类型的数组] [2 boolean类型的数组] [3 OnMultiChoiceClickListener]
        final String[] itme = {"苹果","西瓜","桃子","荔枝"};
        final boolean[] result = {true,false,true,true};
        builder.setMultiChoiceItems(itme, result, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                result[which]= isChecked;
                Toast.makeText(MainActivity.this, "您的选择是"+itme[which], Toast.LENGTH_SHORT).show();
            }
        });
        // TODO: 2017/8/28 多选对话框 最好有[提交]和[取消]这两个按钮
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer buffer = new StringBuffer();
                for (int o = 0;o<result.length;o++){
                    if (result[o]){
                        buffer.append(itme[o]+"\t");
                    }
                }
                Toast.makeText(MainActivity.this, "喜欢:"+buffer.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消",null);
        builder.create().show();
    }


    // TODO: 2017/8/28 进度对话框
    public void click04(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提醒");
        progressDialog.setMessage("正在处理~~~请稍候!");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    // TODO: 2017/8/28 进度条对话框
    public void click05(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        /*
            设置进度条的样式 里面有两种:
                                    1.ProgressDialog.STYLE_SPINNER[圆形样式]
                                    2.ProgressDialog.STYLE_HORIZONTAL[水平方向进度条]
         */
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("提醒");
        progressDialog.setMessage("正在处理...请稍候");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //设置进度条的长度 100 就是走100个格子
                progressDialog.setMax(100);
                try{
                    for (int i = 0;i<=100;i++)
                    {
                        //设置进度条 把for循环里面的 [i] 塞进来
                        progressDialog.setProgress(i);
                        //每 0.1 秒走一个格子
                        Thread.sleep(100);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                //关闭进度条
                progressDialog.dismiss();
            }
        }).start();
        //创建进度条
        progressDialog.show();
    }
}
