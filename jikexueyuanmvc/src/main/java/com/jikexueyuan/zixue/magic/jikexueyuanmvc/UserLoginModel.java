package com.jikexueyuan.zixue.magic.jikexueyuanmvc;

/**
 * 作者：Magic on 2017/8/21 18:24
 * 邮箱：bonian1852@163.com
 * // TODO: 2017/8/21 用户名和密码的接口,和登录成功或失败的接口
 */

public interface UserLoginModel {
    // TODO: 2017/8/21 用户名-密码-验证成功或失败的接口
    void login(String account,String password,UserLoginListener userLoginListener);
}
