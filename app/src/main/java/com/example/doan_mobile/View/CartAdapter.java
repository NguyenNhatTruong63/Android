package com.example.doan_mobile.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan_mobile.Database.Product;
import com.example.doan_mobile.R;
import com.example.doan_mobile.cart.Cart;
import com.example.doan_mobile.cart.CartActivity;
import com.example.doan_mobile.cart.CartItem;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> cartItems;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        }

        ImageView productImage = convertView.findViewById(R.id.product_image);
        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        TextView productQuantity = convertView.findViewById(R.id.product_quantity);
        Button btnRemove = convertView.findViewById(R.id.btn_delete);
        Button btnIncrease = convertView.findViewById(R.id.btn_increase);
        Button btnDecrease = convertView.findViewById(R.id.btn_decrease);

        CartItem cartItem = cartItems.get(position);
        Product product = cartItem.getProduct();

        Glide.with(context).load(product.getImage()).into(productImage);
        productName.setText(product.getName());
        productPrice.setText(String.format("$%.2f", cartItem.getTotalPrice()));
        productQuantity.setText(String.valueOf(cartItem.getQuantity()));

        btnRemove.setOnClickListener(v -> {
            Cart.getInstance().removeFromCart(product);
            notifyDataSetChanged(); // Cập nhật giao diện
            ((CartActivity) context).updateTotalPrice(); // Cập nhật tổng giá tiền
        });


        btnIncrease.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1); // Tăng số lượng
            notifyDataSetChanged(); // Cập nhật giao diện
            ((CartActivity) context).updateTotalPrice(); // Cập nhật tổng giá tiền
        });


        btnDecrease.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                notifyDataSetChanged();
            }
        });


        return convertView;
    }
}