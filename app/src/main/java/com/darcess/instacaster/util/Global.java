package com.darcess.instacaster.util;

import android.location.Location;

/**
 * Created by Alexander Dmitryukov on 7/13/2017.
 */

public class Global {

    public final static String INSTAGRAM_API_BASE = "https://api.instagram.com/v1/";

    public final static String INSTAGRAM_GET_COMMENTS = "locations/{location-id}/media/recent";

    public final static String INSTAGRAM_GET_MEDIA = "media/search";

    public static final String CLIENT_ID = "5eab9987e6994a94ba2d400bfee5f510";
    public static final String CLIENT_SECRET = "913c93d78ba143919538d5cfda21759a";
    public static final String REDIRECT_URI = "https://darcess.com";

    public static final String LONGITUDE_MOCK = "-80.1588699";
    public static final String LATITUDE_MOCK = "26.16505";
    public static final String LOCATION_ID_MOCK = "267076014";

    public static final String ID = "id";
    public static final String USER_NAME = "username";
    public static final String TEXT = "text";
    public static final String POST_URL = "postUrl";
    public static final String PROFILE_URL = "imageUrl";
    public static final String TABLE_NAME = "posts";
    public static final int DB_VERSION = 1;

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    public static final String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;
    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " integer primary key autoincrement not null," +
            USER_NAME + " text not null," +
            TEXT + " text not null," +
            POST_URL + " text not null," +
            PROFILE_URL + " text not null)";
}