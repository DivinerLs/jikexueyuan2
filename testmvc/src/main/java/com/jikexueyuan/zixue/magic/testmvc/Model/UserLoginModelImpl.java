package com.jikexueyuan.zixue.magic.testmvc.Model;

import android.os.Handler;
import android.util.Log;

/**
 * 作者：Magic on 2017/8/21 18:44
 * 邮箱：bonian1852@163.com
 * // TODO: 2017/8/21 具体的实现类[有了接口以后需要在Model层有一个具体的实现类]
 */

public class UserLoginModelImpl implements UserLoginModel {

    private static final String TAG = "UserLoginModelImpl";

    @Override
    public void login(final String account, final String password, final UserLoginListener userLoginListener) {
        // TODO: 2017/8/21 这里是处理登录的业务逻辑
        // TODO: 2017/8/21 模拟网络操作
        Log.d(TAG,"登录中");
        // TODO: 2017/8/21  Delayed [延迟]
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if ("jike".equals(account)&&"123".equals(password))
                {
                    userLoginListener.loginSuccess();
                }else {
                    userLoginListener.loginFailed();
                }
            }
        }, 3000);
    }
}
