package com.tims.pretims;

/**
 * Created by mayocartad on 29.05.2017.
 */

public class Reservation {

    //proprieties
    private String idReservation = null;
    private String resDishName = null;
    private String resDate = null;

    //contructors
    public Reservation()
    {

    }

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

    //set the different proprieties
    public void setId(String reservation)
    {
        this.idReservation = reservation;
    }
    public void setDishName(String dishName)
    {
        this.resDishName = dishName;
    }
    public void setDate(String date)
    {
        this.resDate = date;
    }
}
