package com.prm392.dacare.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.prm392.dacare.MainActivity;
import com.prm392.dacare.R;
import com.prm392.dacare.utils.SharedPreferencesUtil;
import com.prm392.dacare.viewmodel.AuthViewModel;


public class LoginActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferencesUtil.init(this);

        if (SharedPreferencesUtil.getAccessToken() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvResult = findViewById(R.id.tvResult);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập email và mật khẩu!", Toast.LENGTH_SHORT).show();
                return;
            }

            authViewModel.login(email, password);
        });

        authViewModel.getLoginResult().observe(this, loginResponse -> {
            if (loginResponse != null) {
                Log.i("LoginActivity","Login Response Data: " + loginResponse.getMessage());
                tvResult.setText("Đăng nhập thành công!\nTên: " + loginResponse.getData().getName());
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                SharedPreferencesUtil.saveAccessToken(loginResponse.getAccess_token());
                if (!loginResponse.getData().getSkinType().getId().isEmpty()){
                    SharedPreferencesUtil.put("SkinType", loginResponse.getData().getSkinType().getId());
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                tvResult.setText("Đăng nhập thất bại!");
                Toast.makeText(this, "Sai thông tin đăng nhập!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
