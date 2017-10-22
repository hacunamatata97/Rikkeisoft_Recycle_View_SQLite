package com.example.hacunamatata.rikkeisoft_recycle_view.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.hacunamatata.rikkeisoft_recycle_view.entity.User;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserManager.db";
    private static final String USER_TABLE_NAME = "user";
    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PHONE = "phone";

    private static final String CREATE_DB = "create table "
            + USER_TABLE_NAME
            + "\n"
            + "(\n"
            + USER_COLUMN_ID
            + " integer primary key AUTOINCREMENT NOT NULL,\n"
            + USER_COLUMN_NAME
            + " text,\n"
            + USER_COLUMN_PHONE
            + " text\n"
            + ")";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_PHONE, user.getPhone());
        contentValues.put(USER_COLUMN_NAME, user.getName());
        db.insert(USER_TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + USER_TABLE_NAME, null);
        res.moveToFirst();

        do {
            int id = res.getInt(res.getColumnIndex(USER_COLUMN_ID));
            String name = res.getString(res.getColumnIndex(USER_COLUMN_NAME));
            String age = res.getString(res.getColumnIndex(USER_COLUMN_PHONE));

            users.add(new User(id, name, age));
        } while (res.moveToNext());

        res.close();
        db.close();
        return users;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COLUMN_NAME, user.getName());
        contentValues.put(USER_COLUMN_PHONE, user.getPhone());
        return db.update(USER_TABLE_NAME, contentValues, "id=?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE_NAME, USER_COLUMN_ID + "=?",
                new String[] { String.valueOf(user.getId()) });
    }

    public void removeAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE_NAME, null, null);
        db.execSQL("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='" + USER_TABLE_NAME + "';");
    }
}
