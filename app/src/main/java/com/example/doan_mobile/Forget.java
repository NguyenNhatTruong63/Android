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

public class Forget extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        button = findViewById(R.id.changePass1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameEditText = findViewById(R.id.users);
                EditText newPasswordEditText = findViewById(R.id.textnewPass);
                EditText confirmPasswordEditText = findViewById(R.id.textPass);

                String username = usernameEditText.getText().toString().trim();
                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                // Kiểm tra các trường dữ liệu có được nhập đầy đủ không
                if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                    // Hiển thị thông báo lỗi nếu không nhập đầy đủ
                    Toast.makeText(Forget.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else if (!newPassword.equals(confirmPassword)) {
                    // Hiển thị thông báo nếu mật khẩu mới và xác nhận mật khẩu không trùng khớp
                    Toast.makeText(Forget.this, "New password and confirm password do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Gọi phương thức để cập nhật mật khẩu mới vào cơ sở dữ liệu
                    updatePassword(username, newPassword);
                    // Hiển thị thông báo thành công
                    Toast.makeText(Forget.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    // Chuyển người dùng đến màn hình Home
                    Intent intent = new Intent(Forget.this, Login.class);
                    startActivity(intent);
                }
            }
        });

    }
    private void updatePassword(String username, String newPassword) {
        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        DBUserHelper dbHelper = new DBUserHelper(this);
        dbHelper.updatePassword(username, newPassword);
    }
}
