package com.example.doan_mobile.cart;

import com.example.doan_mobile.Database.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items;
    private static Cart instance;

    private Cart() {
        items = new ArrayList<>();
    }

    public static synchronized Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addToCart(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeFromCart(Product product) {
        items.removeIf(item -> item.getProduct().getName().equals(product.getName()));
    }

    public List<CartItem> getItems() {
        return items;
    }
    public double getTotalPrice() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
