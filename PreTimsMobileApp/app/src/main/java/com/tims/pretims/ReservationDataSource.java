package com.tims.pretims;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mayocartad on 29.05.2017.
 */

public class ReservationDataSource {
    private SQLiteDatabase database;
    private ReservationSQLiteHelper dbHelper;
    private String [] columns = {
            ReservationSQLiteHelper.ID,
            ReservationSQLiteHelper.COLUMN_DISH_NAME
    };

    public ReservationDataSource(Context context)
    {
        dbHelper = new ReservationSQLiteHelper(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public void createReservation()
    {

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
