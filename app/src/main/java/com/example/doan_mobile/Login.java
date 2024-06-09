package com.example.doan_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doan_mobile.Database.DBUserHelper;

public class Login extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForget;
    private TextView textViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ các thành phần trong giao diện
        editTextUsername = findViewById(R.id.users);
        editTextPassword = findViewById(R.id.textPass);
        buttonLogin = findViewById(R.id.login);
        textViewForget = findViewById(R.id.forget);
        textViewSignup = findViewById(R.id.signup);

        // Xử lý sự kiện khi người dùng ấn nút "Login"
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(); // Gọi phương thức login()
            }
        });

        // Xử lý sự kiện khi người dùng ấn vào "Quên mật khẩu"
        textViewForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình quên mật khẩu
                Intent intent = new Intent(Login.this, Forget.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện khi người dùng ấn vào "Đăng ký"
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Coffee.class);
                // Chuyển sang màn hình đăng ký
                        startActivity(intent);
            }
        });
    }

    // Phương thức để xác thực thông tin đăng nhập
    private void login() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        DBUserHelper dbHelper = new DBUserHelper(this);
        boolean isAuthenticated = dbHelper.checkLogin(username, password); // Kiểm tra đăng nhập

        if (isAuthenticated) {
            // Đăng nhập thành công, chuyển đến màn hình chính
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish(); // Kết thúc hoạt động đăng nhập
        } else {
            // Đăng nhập không thành công, hiển thị thông báo lỗi
            Toast.makeText(this, "Đăng nhập không thành công. Vui lòng kiểm tra lại tên người dùng và mật khẩu.", Toast.LENGTH_SHORT).show();
        }
    }
}
