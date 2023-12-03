package com.example.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public static final String dbFile = "My_Database.db";
    public static final String tableName = "tbUser";
    public static final String userId = "userId";
    public static final String userName = "userName";
    public static final String userPassword = "userPassword";
    public static final String userPhone = "userPhone";
    public static final String userEmail = "userEmail";
    public static final String userGender = "userGender";
    public static final String userAllowContact = "userAllowContact";

    public DBHelper(@Nullable Context context)  {
        super(context, dbFile, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbCreate = "CREATE TABLE IF NOT EXISTS " + tableName
                + "(" + userId + " TEXT PRIMARY KEY , "
                + userName + " TEXT NOT NULL UNIQUE, "
                + userPassword + " TEXT NOT NULL, "
                + userPhone + " TEXT NOT NULL UNIQUE, "
                + userEmail + " TEXT NOT NULL UNIQUE, "
                + userGender + " TEXT NOT NULL, "
                + userAllowContact + " TEXT NOT NULL)";

        try {
            db.execSQL(dbCreate);
        } catch (Exception exception) {
            //return exception.toString();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public ArrayList<User> getUsers()
    {
        ArrayList<User> allUsers = new ArrayList<>();
        User u;

        try {
            SQLiteDatabase sqdb = this.getReadableDatabase();
            Cursor cursor = sqdb.query(tableName, null, null, null, null, null, null, null);
            if (cursor.getCount() == 0)
            {
                sqdb.close();
                return null;
            }
            cursor.moveToFirst();

            while (!cursor.isAfterLast())
            {
                u = getCurrentUser(cursor);
                allUsers.add(u);
                cursor.moveToNext();
                // return "good";
            }
            sqdb.close();
            return allUsers;
        }
        catch (Exception exception)
        {
            System.err.println("ERROR: Couldn't read user");
            throw exception;
        }
    }

    @NonNull
    public static User getCurrentUser(Cursor cursor) {
        User u;
        u = new User();
        u.setId(cursor.getString((int) cursor.getColumnIndex (userId)));
        u.setUsername(cursor.getString((int) cursor.getColumnIndex (userName)));
        u.setPassword(cursor.getString((int) cursor.getColumnIndex (userPassword)));
        u.setPhone(cursor.getString((int) cursor.getColumnIndex (userPhone)));
        u.setEmail(cursor.getString((int) cursor.getColumnIndex(userEmail)));
        u.setGender(cursor.getString((int) cursor.getColumnIndex (userGender)));
        u.setAllowContact(cursor.getString((int) cursor.getColumnIndex (userAllowContact)));
        return u;
    }

    public String addUser(User user) {
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.userId, user.getId());
        cv.put(DBHelper.userName, user.getUsername());
        cv.put(DBHelper.userPassword, user.getPassword());
        cv.put(DBHelper.userPhone, user.getPhone());
        cv.put(DBHelper.userEmail, user.getEmail());
        cv.put(DBHelper.userGender, user.getGender());
        cv.put(DBHelper.userAllowContact, user.getAllowContact());

        try{
            SQLiteDatabase sqdb = this.getReadableDatabase();
            sqdb.insert(tableName, null,cv);
            sqdb.close();
            return "success";
        }
        catch (Exception exception)
        {
            exception.toString();
        }
        return null;

    }
    
    public Boolean confirmCredentials(String username, String password) {
        SQLiteDatabase sqdb = this.getReadableDatabase();
        Cursor cursor = sqdb.query(tableName, null,
                DBHelper.userName + "= ? AND " + DBHelper.userPassword + "= ?"
                , new String[] {username, password}, null, null, null, null);
        cursor.moveToFirst();
        Boolean result = cursor.getCount() != 0;
        cursor.close();
        sqdb.close();
        return result;
    }
    
    public Boolean updateCredentials(String username, String oldPassword, String newPassword) {
        if (!confirmCredentials(username, oldPassword)) {
            return false;
        }
        
        SQLiteDatabase sqdb  = this.getWritableDatabase();
        
        ContentValues contentValues = new ContentValues();
        contentValues.put(userPassword, newPassword);
        
        sqdb.update(tableName, contentValues, userName + "=? AND " + userPassword + "=?", new String[] {username, oldPassword});
        sqdb.close();
        return true;
    }


}
