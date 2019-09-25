package com.example.databaselabe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseFile extends SQLiteOpenHelper {
    private SQLiteDatabase database;

    public DatabaseFile open() throws SQLException {

        this.database = this.getWritableDatabase();
        return this;
    }


    public void close() {
close();
    }
    public DatabaseFile(Context context) {
        super(context, "product.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create Table
        db.execSQL("create table products(Id Int primary key,name varchar(50),category varchar(50),prize double)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+"products");
        onCreate(db);
    }

    //Insert Data Method
    public void Addproducts(int id,String name,String category,Double prize)
    {
        SQLiteDatabase dtabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //Table Column
        contentValues.put("Id",id);
        contentValues.put("name",name);
        contentValues.put("category",category);
        contentValues.put("prize",prize);

        //Table Name
        dtabase.insert("products",null,contentValues);
    }


    //Update Data Method
    public void UpdateProduct(int id,String name,String category,Double prize)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("prize",prize);
        contentValues.put("category",category);

        database.update("products",contentValues,"id=?",new String[]{Integer.toString(id)});
    }

    //Delete Data Method
    public void Delete(int id)
    {
        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        database.delete("products","id=?",new String[]{Integer.toString(id)});
    }

    //Select Data and Show in text But Now Here is Error
    public String[] getAppCategoryDetail() {

        final String TABLE_NAME = "products";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String data[]      = null;

        if (cursor.moveToFirst()) {
            do {
                // get the data into array, or class variable
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }

    public Cursor fetch(int id) {
        final String TABLE_NAME = "products";

        String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
        SQLiteDatabase db  = this.getReadableDatabase();

        Cursor cursor      = db.rawQuery(selectQuery, null);
        //Cursor cursor = this.database.query("products", new String[]{Integer.toString(id), "name", "category","prize"}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
