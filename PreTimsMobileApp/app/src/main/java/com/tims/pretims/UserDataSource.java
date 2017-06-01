package com.tims.pretims;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mayocartad on 24.05.2017.
 */

public class UserDataSource {

    private SQLiteDatabase database;
    private UserSQLiteHelper dbHelper;
    private String [] columns = {
            UserSQLiteHelper.ID,
            UserSQLiteHelper.COLUMN_LOGIN,
            UserSQLiteHelper.COLUMN_PASSWORD,
            UserSQLiteHelper.COLUMN_TOKEN
    };

    public UserDataSource(Context context)
    {
        dbHelper = new UserSQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public User createUser(String login, String password, String token)
    {
        ContentValues values = new ContentValues();

        values.put(UserSQLiteHelper.COLUMN_LOGIN, login);
        values.put(UserSQLiteHelper.COLUMN_PASSWORD, password);
        values.put(UserSQLiteHelper.COLUMN_TOKEN, token);

        long insertId = database.insert(UserSQLiteHelper.TABLE_USER, null, values);

        Cursor cursor = database.query(
                UserSQLiteHelper.TABLE_USER, // String : nom de la table
                columns, //String[] : liste des colonnes à retourner
                UserSQLiteHelper.ID + " = " + insertId, // String : clauses "WHERE"
                null, //String[] : selectionArgs
                null, //String[] : groupBy
                null, //String[] : having
                null); //String[] : orderBy

        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public String getLastToken()
    {
        User user = null;

        Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER,
                columns, null, null, null, null, null);

        if(cursor.getCount() != 0)
        {
            cursor.moveToLast();
            user = cursorToUser(cursor);
            cursor.close();
            return user.getToken();
        }
        else
        {
            return null;
        }
    }

    public User getLastUser()
    {
        User user = null;

        Cursor cursor = database.query(UserSQLiteHelper.TABLE_USER,
                columns, null, null, null, null, null);

        if(cursor.getCount() != 0)
        {
            //get user
            cursor.moveToLast();
            user = cursorToUser(cursor);
            cursor.close();
        }
        return user;
    }

    private void modifyUser(int id, ContentValues values)
    {
        database.update(UserSQLiteHelper.TABLE_USER, values, UserSQLiteHelper.ID+" = "+ id, null);
    }

    public void deleteLastToken()
    {
        User user = getLastUser();
        user.setToken(null);

        //create the values
        ContentValues values = new ContentValues();
        values.put(UserSQLiteHelper.COLUMN_LOGIN, user.getLogin());
        values.put(UserSQLiteHelper.COLUMN_PASSWORD, user.getPassword());
        values.put(UserSQLiteHelper.COLUMN_TOKEN, user.getToken());

        modifyUser(user.getId(), values);

    }

    private User cursorToUser(Cursor cursor)
    {
        User user = new User();

        user.setId(cursor.getInt(0));
        user.setLogin(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        user.setToken(cursor.getString(3));

        return user;
    }
}
