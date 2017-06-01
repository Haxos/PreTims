package com.tims.pretims;

import okhttp3.MediaType;

/**
 * Created by mayocartad on 19.05.2017.
 */

public class AllConstants {
    public static final String DATABASE_SQLITE = "db_pretims";
    public static final int DATABASE_SQLITE_VERSION = 1;
    private static final String DOMAIN_NAME = "http://172.30.30.1/tims/public/index.php/api/";
    public static final String GET_DISH_LIST = DOMAIN_NAME+"dish";
    public static final String POST_CRDENTIALS = DOMAIN_NAME+"login";
    //the route for store a dish will be use for storing a reservation
    public static final String POST_RESERVATION = DOMAIN_NAME+"dish";
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    public static final String NAME_DIRECTORY_DISH_IMAGE = "imageDir";
    public static final String NAME_FILE_DISH_IMAGE = "dishImage.jpg";
}
