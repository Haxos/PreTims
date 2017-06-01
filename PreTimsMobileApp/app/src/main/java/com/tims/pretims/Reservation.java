package com.tims.pretims;

/**
 * Created by mayocartad on 29.05.2017.
 */

public class Reservation {

    //proprieties
    private String idReservation;
    private String resDishName;
    private String resDate;

    //contructors
    public Reservation(String id, String dishName, String date)
    {
        idReservation = id;
        resDishName = dishName;
        resDate = date;
    }

    //gets the different proprieties
    public String getId()
    {
        return idReservation;
    }
    public String getDishName()
    {
        return resDishName;
    }
    public String getDate()
    {
        return resDate;
    }
}
