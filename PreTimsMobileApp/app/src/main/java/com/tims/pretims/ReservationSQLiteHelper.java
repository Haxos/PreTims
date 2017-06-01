package com.tims.pretims;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mayocartad on 29.05.2017.
 */

public class ReservationSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_RESERVATION = "t_reservation";
    public static final String ID = "idReservation";
    public static final String COLUMN_DISH_NAME = "resDishName";
    public static final String COLUMN_DATE = "resDate";


    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "+ TABLE_RESERVATION
            + "("
            + ID + " integer primary key autoincrement, "
            + COLUMN_DISH_NAME + " varchar null,"
            + COLUMN_DATE + " date"
            + ");";

    public ReservationSQLiteHelper(Context context) {
        super(context, AllConstants.DATABASE_SQLITE, null, AllConstants.DATABASE_SQLITE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ReservationSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + COLUMN_DISH_NAME);
        onCreate(db);
    }
}
