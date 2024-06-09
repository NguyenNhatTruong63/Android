package com.example.doan_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan_mobile.Database.DBUserHelper;

public class Signup extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText fullnameEditText;
    private Button signupButton;

    private DBUserHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Ánh xạ các thành phần giao diện
        usernameEditText = findViewById(R.id.users);
        passwordEditText = findViewById(R.id.textnewPass);
        fullnameEditText = findViewById(R.id.textEmail);
        signupButton = findViewById(R.id.signup_button);

        // Khởi tạo đối tượng DBUserHelper
        dbHelper = new DBUserHelper(this);

        // Lắng nghe sự kiện nhấn nút đăng ký
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các trường nhập liệu
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String fullname = fullnameEditText.getText().toString().trim();

                // Kiểm tra xem các trường có rỗng không
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(fullname)) {
                    // Thêm dữ liệu người dùng mới vào cơ sở dữ liệu
                    long result = dbHelper.addUser(username, password, fullname);
                    if (result != -1) {
                        // Đăng ký thành công
                        Toast.makeText(Signup.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        // Chuyển đến màn hình đăng nhập sau khi đăng ký thành công
                        Intent intent = new Intent(Signup.this, Login.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Đăng ký thất bại
                        Toast.makeText(Signup.this, "Đăng ký thất bại. Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Hiển thị thông báo nếu có trường rỗng
                    Toast.makeText(Signup.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}