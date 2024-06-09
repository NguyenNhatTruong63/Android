package com.example.doan_mobile.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseProductHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "product.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_PRODUCTS = "product";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_PRICE = "price";

    public DatabaseProductHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_IMAGE + " INTEGER,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_IMAGE, product.getImage());
        values.put(COLUMN_TYPE, product.getType());
        values.put(COLUMN_PRICE, product.getPrice());
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_PRODUCTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                if (idIndex != -1) {
                    product.setId(cursor.getInt(idIndex));
                } else {
                    // Xử lý trường hợp cột không tồn tại
                }
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                if (nameIndex != -1) {
                    product.setName(cursor.getString(nameIndex));
                } else {
                    // Xử lý trường hợp cột không tồn tại
                }
                int imageIndex = cursor.getColumnIndex(COLUMN_IMAGE);
                if (imageIndex != -1) {
                    product.setImage(cursor.getInt(imageIndex));
                } else {
                    // Xử lý trường hợp cột không tồn tại
                }
                int typeIndex = cursor.getColumnIndex(COLUMN_TYPE);
                if (typeIndex != -1) {
                    product.setType(cursor.getString(typeIndex));
                } else {
                    // Xử lý trường hợp cột không tồn tại
                }
                int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
                if (priceIndex != -1) {
                    product.setPrice(cursor.getDouble(priceIndex));
                } else {
                    // Xử lý trường hợp cột không tồn tại
                }
                productList.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return productList;
    }

}
