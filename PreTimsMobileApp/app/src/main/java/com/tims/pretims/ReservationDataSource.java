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
            ReservationSQLiteHelper.COLUMN_DISH_NAME,
            ReservationSQLiteHelper.COLUMN_DATE
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

    public Reservation createReservation(String dishName, String date)
    {
        ContentValues values = new ContentValues();

        values.put(ReservationSQLiteHelper.COLUMN_DISH_NAME, dishName);
        values.put(ReservationSQLiteHelper.COLUMN_DATE, date);

        long insertId = database.insert(ReservationSQLiteHelper.TABLE_RESERVATION, null, values);

        Cursor cursor = database.query(
                ReservationSQLiteHelper.TABLE_RESERVATION, // String : nom de la table
                columns, //String[] : liste des colonnes à retourner
                ReservationSQLiteHelper.ID + " = " + insertId, // String : clauses "WHERE"
                null, //String[] : selectionArgs
                null, //String[] : groupBy
                null, //String[] : having
                null); //String[] : orderBy

        cursor.moveToFirst();
        Reservation newReservation = cursorToReservation(cursor);
        cursor.close();
        return newReservation;
    }

    public Reservation getLastReservation()
    {
        Reservation reservation = new Reservation();

        Cursor cursor = database.query(ReservationSQLiteHelper.TABLE_RESERVATION,
                columns, null, null, null, null, null);

        if(cursor.getCount() != 0)
        {
            //get reservation
            cursor.moveToLast();
            reservation = cursorToReservation(cursor);
            cursor.close();
        }
        return reservation;
    }

//    private void modifyUser(int id, ContentValues values)
//    {
//        database.update(UserSQLiteHelper.TABLE_USER, values, UserSQLiteHelper.ID+" = "+ id, null);
//    }

    public void deleteLastReservation()
    {
        Reservation reservation = getLastReservation();

        database.delete(ReservationSQLiteHelper.TABLE_RESERVATION, //table name
                ReservationSQLiteHelper.ID + "=" + reservation.getId(), //Where clause
                null    //args[]
        );
    }

    private Reservation cursorToReservation(Cursor cursor)
    {
        Reservation reservation = new Reservation();

        reservation.setId(cursor.getString(0));
        reservation.setDishName(cursor.getString(1));
        reservation.setDate(cursor.getString(2));

        return reservation;
    }
}
