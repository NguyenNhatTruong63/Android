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
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "orders.db";
    private static final String TABLE_ORDERS = "orders";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "product_name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_PRICE = "price";

    public OrderHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDERS_TABLE = "CREATE TABLE " + TABLE_ORDERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_QUANTITY + " INTEGER,"
                + COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_ORDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDERS);
        onCreate(db);
    }

    public void addOrder(CartItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, item.getProduct().getName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_PRICE, item.getProduct().getPrice());
        db.insert(TABLE_ORDERS, null, values);
        db.close();
    }
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_ORDERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor != null && cursor.getCount() > 0) {
            // Di chuyển con trỏ đến hàng đầu tiên
            if (cursor.moveToFirst()) {
                do {
                    // Lấy giá trị từ các cột theo tên cột
                    int orderId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)); // Lấy id
                    String productName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_NAME)); // Lấy tên sản phẩm
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_QUANTITY)); // Lấy số lượng
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)); // Lấy giá

                    // Tạo đối tượng Order từ dữ liệu cột
                    Order order = new Order(orderId, productName, quantity, price);
                    // Thêm order vào danh sách
                    orderList.add(order);
                } while (cursor.moveToNext());
            }
        }

        // Đóng kết nối và trả về danh sách đơn hàng
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return orderList;
    }

}
