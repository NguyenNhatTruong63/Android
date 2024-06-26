package com.example.doan_mobile.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doan_mobile.Database.OrderHelper;
import com.example.doan_mobile.Home;
import com.example.doan_mobile.Login;
import com.example.doan_mobile.ProductDetailActivity;
import com.example.doan_mobile.R;
import com.example.doan_mobile.View.CartAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView listView;
    private CartAdapter adapter;
    private Button btnCheckout,btn_back_home;
    private TextView totalPriceTextView;
    private EditText addressEditText;
    private OrderHelper orderHelper;
    private UserSessionManager session;
    private ImageView backCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        backCheckout = findViewById(R.id.backCheckout);
        backCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ProductDetailActivity.class);
                startActivity(intent);
            }
        });

        btn_back_home = findViewById(R.id.btn_back_home);
        btn_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, Home.class);
                startActivity(intent);
            }
        });

        session = new UserSessionManager(getApplicationContext());
        totalPriceTextView = findViewById(R.id.total_price);
        listView = findViewById(R.id.cart_list);
        btnCheckout = findViewById(R.id.btn_checkout_cart);
        addressEditText = findViewById(R.id.addressEditText);

        List<CartItem> cartItems = Cart.getInstance().getItems();
        adapter = new CartAdapter(this, cartItems);
        listView.setAdapter(adapter);

        orderHelper = new OrderHelper(this);
        btnCheckout.setOnClickListener(v -> {
            if (!session.isLoggedIn()) {
                Intent intent = new Intent(CartActivity.this, Login.class);
                startActivity(intent);
            } else {
                saveOrderToDatabase();
            }
        });
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        double totalPrice = Cart.getInstance().getTotalPrice();
        totalPriceTextView.setText(String.format("Tổng tiền: $%.2f", totalPrice));
    }

    private void saveOrderToDatabase() {
        String customerName = session.getUserDetails().get(UserSessionManager.KEY_NAME);
        String address = addressEditText.getText().toString().trim(); // Lấy địa chỉ từ EditText
        List<CartItem> cartItems = Cart.getInstance().getItems();
        for (CartItem item : cartItems) {
            orderHelper.addOrder(item, customerName, address);
        }
        Toast.makeText(this, "Đơn hàng đã được đặt thành công", Toast.LENGTH_SHORT).show();
        // Xóa giỏ hàng sau khi đặt hàng thành công
        Cart.getInstance().clearCart();
    }

}
