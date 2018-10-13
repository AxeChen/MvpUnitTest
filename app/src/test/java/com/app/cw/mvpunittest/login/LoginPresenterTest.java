package com.app.cw.mvpunittest.login;

import com.app.cw.mvpunittest.network.response.Response;
import com.app.cw.mvpunittest.network.schedulers.SchedulerTestProvider;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 登录Presenter的测试类
 */
public class LoginPresenterTest {

    @Mock
    private LoginContract.Model model;

    @Mock
    private LoginContract.View view;

    private LoginPresenter presenter;

    @Mock
    private SchedulerTestProvider schedulerProvider;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new SchedulerTestProvider();
        presenter = new LoginPresenter(model, view,schedulerProvider);
    }

    /**
     * 登陆成功
     * @throws Exception
     */
    @Test
    public void loginSuccess() throws Exception {
        // 1、断言model.login的方法返回正确的Response数据
        when(model.login("123321","123321")).thenReturn(Observable.just(new Response<>(0,new JSONObject(),"")));
        // 2、Presenter执行登录逻辑
        presenter.login("123321","123321");
        // 3、预测回调给view层的方法是否被调用
        verify(view).loginSuccess();
    }

    /**
     * 登录失败，服务器返回错误的code
     * @throws Exception
     */
    @Test
    public void loginFailByServer() throws Exception{
        // 1、断言model.login的方法返回正确的Response数据
        when(model.login("123321","123321")).thenReturn(Observable.just(new Response<>(1,new JSONObject(),"")));
        // 2、Presenter执行登录逻辑
        presenter.login("123321","123321");
        // 3、预测回调给view层的方法是否被调用
        verify(view).loginFail();
    }

    /**
     * 登录失败，错误的用户名
     * @throws Exception
     */
    @Test
    public void loginFailByErrorUserName() throws Exception{
        presenter.login("","123321");
        // 3、预测回调给view层的方法是否被调用
        verify(view).loginFailCauseByErrorUserName();
    }

    /**
     * 登录失败，错误的密码
     * @throws Exception
     */
    @Test
    public void loginFailByErrorPwd() throws Exception{
        presenter.login("123321","");
        // 3、预测回调给view层的方法是否被调用
        verify(view).loginFailCauseByErrorPassword();
    }

}