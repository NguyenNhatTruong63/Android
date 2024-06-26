package com.example.doan_mobile.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doan_mobile.Order;
import com.example.doan_mobile.cart.CartItem;

import java.util.ArrayList;
import java.util.List;

public class OrderHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "orders";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_CUSTOMER_NAME = "customer_name";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_PRODUCT_IMAGE = "product_image";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_ADDRESS = "address";

    private SQLiteDatabase database;

    public OrderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_CUSTOMER_NAME + " TEXT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_IMAGE + " INTEGER,"
                + COLUMN_QUANTITY + " INTEGER,"
                + COLUMN_PRICE + " REAL,"
                + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public void addOrder(CartItem item, String customerName, String address) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_NAME, customerName);
        values.put(COLUMN_PRODUCT_NAME, item.getProduct().getName());
        values.put(COLUMN_PRODUCT_IMAGE, item.getProduct().getImage());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_PRICE, item.getProduct().getPrice());
        values.put(COLUMN_ADDRESS, address);

        database.insert(TABLE_ORDERS, null, values);
    }

    public List<Order> getOrderHistory(String customerName) {
        List<Order> orders = new ArrayList<>();
        Cursor cursor = database.query(TABLE_ORDERS, null, COLUMN_CUSTOMER_NAME + "=?", new String[]{customerName}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Order order = new Order();
                // Kiểm tra chỉ mục cột trước khi truy cập
                int productNameIndex = cursor.getColumnIndex(COLUMN_PRODUCT_NAME);
                if (productNameIndex != -1) {
                    order.setProductName(cursor.getString(productNameIndex));
                }

                int productImageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);
                if (productImageIndex != -1) {
                    order.setProductImage(cursor.getInt(productImageIndex));
                }

                int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
                if (priceIndex != -1) {
                    order.setPrice(cursor.getDouble(priceIndex));
                }

                int quantityIndex = cursor.getColumnIndex(COLUMN_QUANTITY);
                if (quantityIndex != -1) {
                    order.setQuantity(cursor.getInt(quantityIndex));
                }

                int addressIndex = cursor.getColumnIndex(COLUMN_ADDRESS);
                if (addressIndex != -1) {
                    order.setAddress(cursor.getString(addressIndex));
                }

                orders.add(order);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return orders;
    }
}
