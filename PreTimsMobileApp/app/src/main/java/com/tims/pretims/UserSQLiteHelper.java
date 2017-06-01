package com.tims.pretims;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mayocartad on 23.05.2017.
 */

public class UserSQLiteHelper extends SQLiteOpenHelper
{
    public static final String TABLE_USER = "t_user";
    public static final String ID = "idUser";
    public static final String COLUMN_LOGIN = "useLogin";
    public static final String COLUMN_PASSWORD = "usePassword";
    public static final String COLUMN_TOKEN = "useToken";


    // Commande sql pour la création de la base de données
    public static final String TABLE_CREATE = "create table "+ TABLE_USER
            + "("
            + ID + " integer primary key autoincrement, "
            + COLUMN_LOGIN + " varchar null,"
            + COLUMN_PASSWORD + " varchar null,"
            + COLUMN_TOKEN + " varchar null"
            + ");";

    public UserSQLiteHelper(Context context) {
        super(context, AllConstants.DATABASE_SQLITE, null, AllConstants.DATABASE_SQLITE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(TABLE_CREATE);
        database.execSQL(ReservationSQLiteHelper.TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(UserSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + COLUMN_LOGIN);
        onCreate(db);
    }
}
