package com.app.cw.mvpunittest.login;

import com.app.cw.mvpunittest.network.response.Response;

import org.json.JSONObject;

import io.reactivex.Observable;

/**
 * 登录contract
 */
public interface LoginContract {
    public interface View {
        // 登录成功
        void loginSuccess();

        // 登录失败
        void loginFail();

        // 登录失败，因为用户名错误
        void loginFailCauseByErrorUserName();

        // 登录失败，因为用户名错误
        void loginFailCauseByErrorPassword();
    }

    public interface Model {
        Observable<Response<JSONObject>> login(String userName, String userPwd);
    }

    public interface Presenter {
        void login(String userName, String userPwd);
    }
}
