package com.app.cw.mvpunittest.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.cw.mvpunittest.R;

/**
 * 登录View层
 */
public class LoginAciivity extends AppCompatActivity implements LoginContract.View {

    private EditText edUserName;
    private EditText edPassword;
    private Button btnLogin;

    private LoginContract.Presenter presenter;

    private LoginContract.Model model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        model = new LoginModel();
        presenter = new LoginPresenter(model, this);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            String userName = edUserName.getText().toString();
            String userPwd = edPassword.getText().toString();
            presenter.login(userName,userPwd);
        });
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailCauseByErrorUserName() {
        Toast.makeText(this, "登录失败，用户名错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailCauseByErrorPassword() {
        Toast.makeText(this, "登录失败，密码错误", Toast.LENGTH_SHORT).show();
    }
}
