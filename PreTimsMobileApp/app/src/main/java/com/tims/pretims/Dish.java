package com.tims.pretims;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mayocartad on 18.05.2017.
 */

public class Dish implements Serializable{

    //proprieties
    private String idDish;
    private String disName;
    private String disComposition;
    private String disDescription;
    private Bitmap disImage;

    //contructors
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

//    @Override
//    /**
//     * Write the differents values to parcel
//     */
//    public void writeToParcel(Parcel dest, int flags)
//    {
//        dest.writeString(idDish);
//        dest.writeString(disName);
//        dest.writeString(disDescription);
//        dest.writeString(disComposition);
//        dest.writeValue(disImage);
//    }
//
//    @Override
//    public int describeContents()
//    {
//        //ignore
//        return 0;
//    }
//
//    private Dish(Parcel in)
//    {
//        idDish = in.readString();
//        disName = in.readString();
//        disDescription = in.readString();
//        disComposition = in.readString();
//        disImage = (Bitmap) in.readValue(Bitmap.class.getClassLoader());
//    }
//
//    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
//        @Override
//        public Dish createFromParcel(Parcel source) {
//            return new Dish(source);
//        }
//
//        @Override
//        public Dish[] newArray(int size) {
//            return new Dish[size];
//        }
//    };
}
