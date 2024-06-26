package com.example.doan_mobile.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan_mobile.Order;
import com.example.doan_mobile.R;

import org.w3c.dom.Text;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView orderIdTextView;
        private TextView orderNameTextView;
        private TextView orderQuantityTextView;
        private TextView orderPriceTextView;
        // Add more views as needed

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.order_id);
            orderNameTextView = itemView.findViewById(R.id.order_name);
            orderQuantityTextView = itemView.findViewById(R.id.order_quantity);
            orderPriceTextView = itemView.findViewById(R.id.order_price);
            // Initialize other views
        }

        public void bind(Order order) {
            orderIdTextView.setText("Order ID: " + order.getOrderId());
            orderNameTextView.setText("Order ID: " + order.getProductName());
            orderQuantityTextView.setText("Order ID: " + order.getQuantity());
            orderPriceTextView.setText("Order ID: " + order.getPrice());

            // Bind other data
        }
    }
}
