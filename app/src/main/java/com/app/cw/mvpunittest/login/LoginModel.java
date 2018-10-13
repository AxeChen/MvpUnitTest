package com.app.cw.mvpunittest.login;

import com.app.cw.mvpunittest.network.NetWorkManager;
import com.app.cw.mvpunittest.network.response.Response;

import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 登录Model层
 */
public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<Response<JSONObject>> login(String userName, String userPwd) {
        return NetWorkManager.getRequest().login(userName,userPwd);
    }
}
