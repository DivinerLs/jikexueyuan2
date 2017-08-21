package com.jikexueyuan.zixue.magic.testmvc;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jikexueyuan.zixue.magic.testmvc.Model.UserLoginListener;
import com.jikexueyuan.zixue.magic.testmvc.Model.UserLoginModel;
import com.jikexueyuan.zixue.magic.testmvc.Model.UserLoginModelImpl;

public class MVCLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_account,et_pwd;
    private AppCompatButton btn_login,btn_clear;

    // TODO: 2017/8/21 调用Model模型
    private UserLoginModel loginModel;

    private String password = "";
    private String account = "";

    private static final String TAG = "MVCLoginActivity";

    // TODO: 2017/8/21 进度对话框
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO: 2017/8/21 一定要想init();控件 再去  initListener() 不然会爆空指针异常
        init();
        initListener();
    }

    private void init() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("登录中,请稍候...");
        // TODO: 2017/8/21 setCancelable [是否可以取消]
        // TODO: 2017/8/21 progressDialog.setCancelable(false); 设置成阻塞的
        progressDialog.setCancelable(false);
        // TODO: 2017/8/21 实例化 UserLoginModelImpl()
        loginModel = new UserLoginModelImpl();
        et_account = (EditText) findViewById(R.id.et_account);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        btn_clear = (AppCompatButton) findViewById(R.id.btn_clear);
    }


    public void initListener() {
        btn_login.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login:
                // TODO: 2017/8/21 这里是一个业务逻辑的控制
                account = et_account.getText().toString();
                password = et_pwd.getText().toString();
                if (TextUtils.isEmpty(account)||TextUtils.isEmpty(password))
                {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    // TODO: 2017/8/21 登录的时候需要将 progressDialog 进行显示
                    progressDialog.show();// TODO: 2017/8/21 progressDialog.show() 显示进度条
                    loginModel.login(account, password, new UserLoginListener() {
                        @Override
                        public void loginSuccess() {
                            // TODO: 2017/8/21 成功或失败都需要将 progressDialog 进行关闭
                            progressDialog.dismiss();
                            Log.d(TAG,"loginSuccess: 登录成功");
                            Toast.makeText(MVCLoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void loginFailed() {
                            // TODO: 2017/8/21 成功或失败都需要将 progressDialog 进行关闭
                            progressDialog.dismiss();
                            Log.d(TAG, "loginFailed: 登录失败");
                            Toast.makeText(MVCLoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;

            case R.id.btn_clear:
                et_account.setText("");
                et_pwd.setText("");
                break;
        }
    }
}
