package com.app.cw.mvpunittest.login;

import android.text.TextUtils;

import com.app.cw.mvpunittest.network.response.ResponseTransformer;
import com.app.cw.mvpunittest.network.schedulers.BaseSchedulerProvider;
import com.app.cw.mvpunittest.network.schedulers.SchedulerProvider;
import com.app.cw.mvpunittest.network.schedulers.SchedulerTestProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 登录Presenter层
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.Model model;
    private LoginContract.View view;
    private BaseSchedulerProvider schedulerProvider;
    private CompositeDisposable mDisposable;

    public LoginPresenter(LoginContract.Model model,LoginContract.View view){
        this.view = view;
        this.model = model;
        mDisposable = new CompositeDisposable();
        schedulerProvider = SchedulerProvider.getInstance();
    }

    /**
     * 这个是为单元测试建的构造函数，原因是因为Rxjava线程切换，必须设置为立即执行才能测试通过
     * @param model
     * @param view
     * @param schedulerProvider
     */
    public LoginPresenter(LoginContract.Model model,LoginContract.View view,SchedulerTestProvider schedulerProvider){
        this.view = view;
        this.model = model;
        mDisposable = new CompositeDisposable();
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void login(String userName, String userPwd) {
        if (TextUtils.isEmpty(userName)) {
            view.loginFailCauseByErrorUserName();
            return;
        }
        if (TextUtils.isEmpty(userPwd)) {
            view.loginFailCauseByErrorPassword();
            return;
        }
        Disposable disposable = model.login(userName, userPwd).
                compose(ResponseTransformer.handleResult()).
                compose(schedulerProvider.applySchedulers())
                .subscribe(jsonObject -> {
                            view.loginSuccess();
                        },
                        throwable -> {
                            view.loginFail();
                        });
        mDisposable.add(disposable);
    }
}
