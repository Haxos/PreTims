package com.tims.pretims;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by mayocartad on 18.05.2017.
 */

public class Dish{

    //proprieties
    private String idDish;
    private String disName;
    private String disComposition;
    private String disDescription;
    private Bitmap disImage;

    //contructors
    public Dish()
    {

    }

    public Dish(String id, String name, String composition, String description, Bitmap image)
    {
        idDish = id;
        disName = name;
        disComposition = composition;
        disDescription = description;
        disImage = image;
    }

    //gets the different proprieties
    public String getId()
    {
        return idDish;
    }
    public String getName()
    {
        return disName;
    }
    public String getComposition()
    {
        return disComposition;
    }
    public String getDescription()
    {
        return disDescription;
    }
    public Bitmap getImage()
    {
        return disImage;
    }
}
