package com.jikexueyuan.zixue.magic.testmvc.Model;

/**
 * 作者：Magic on 2017/8/21 18:41
 * 邮箱：bonian1852@163.com
 * // TODO: 2017/8/21 用户名和密码的接口,和登录成功或失败的接口
 */

public interface UserLoginModel {

    void login(String account,String password,UserLoginListener userLoginListener);
}
