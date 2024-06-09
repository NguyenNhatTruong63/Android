package com.example.doan_mobile.cart;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_mobile.Database.OrderHelper;
import com.example.doan_mobile.R;
import com.example.doan_mobile.View.CartAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView listView;
    private CartAdapter adapter;
    private Button btnCheckout;
    private TextView totalPriceTextView;
    private OrderHelper orderHelper; // Thêm biến instance của OrderHelper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        totalPriceTextView = findViewById(R.id.total_price);
        listView = findViewById(R.id.cart_list);
        btnCheckout = findViewById(R.id.btn_checkout_cart);

        List<CartItem> cartItems = Cart.getInstance().getItems();
        adapter = new CartAdapter(this, cartItems);
        listView.setAdapter(adapter);

        orderHelper = new OrderHelper(this);
        btnCheckout.setOnClickListener(v -> {
            // Xử lý thanh toán
            saveOrderToDatabase();
        });
        updateTotalPrice();
    }
    public void updateTotalPrice() {
        double totalPrice = Cart.getInstance().getTotalPrice();
        totalPriceTextView.setText(String.format("Total: $%.2f", totalPrice));
    }
    private void saveOrderToDatabase() {
        List<CartItem> cartItems = Cart.getInstance().getItems();
        for (CartItem item : cartItems) {
            orderHelper.addOrder(item); // Thêm từng mục đơn hàng vào cơ sở dữ liệu
        }
        // Hiển thị thông báo đặt hàng thành công
        Toast.makeText(this, "Đơn hàng đã được đặt thành công", Toast.LENGTH_SHORT).show();
    }

}