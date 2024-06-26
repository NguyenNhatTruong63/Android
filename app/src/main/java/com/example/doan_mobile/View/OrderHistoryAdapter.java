package com.example.doan_mobile.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doan_mobile.Order;
import com.example.doan_mobile.R;

import java.util.List;

public class OrderHistoryAdapter extends ArrayAdapter<Order> {
    private Context context;
    private List<Order> orders;

    public OrderHistoryAdapter(Context context, List<Order> orders) {
        super(context, 0, orders);
        this.context = context;
        this.orders = orders;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.order_history_item, parent, false);
        }

        Order order = orders.get(position);

        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        TextView quantity = convertView.findViewById(R.id.quantity);
        TextView address = convertView.findViewById(R.id.address);
        ImageView productImage = convertView.findViewById(R.id.product_image);

        productName.setText(order.getProductName());
        productPrice.setText(String.format("$%.2f", order.getPrice()));
        quantity.setText(String.format("Quantity: %d", order.getQuantity()));
        address.setText(order.getAddress());
        Glide.with(context).load(order.getProductImage()).into(productImage);

        return convertView;
    }
}
