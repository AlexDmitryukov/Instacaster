package com.darcess.instacaster.mvp.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.darcess.instacaster.api.post.Datum;
import com.darcess.instacaster.ui.MainActivity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.darcess.instacaster.util.Global.CREATE_TABLE;
import static com.darcess.instacaster.util.Global.DB_VERSION;
import static com.darcess.instacaster.util.Global.POST_URL;
import static com.darcess.instacaster.util.Global.PROFILE_URL;
import static com.darcess.instacaster.util.Global.SELECT_QUERY;
import static com.darcess.instacaster.util.Global.TABLE_NAME;
import static com.darcess.instacaster.util.Global.DROP_TABLE;

import static com.darcess.instacaster.util.Global.TEXT;
import static com.darcess.instacaster.util.Global.USER_NAME;

/**
 * Created by Alexander Dmitryukov on 7/17/2017.
 */

public class Storage extends SQLiteOpenHelper {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    public Storage(Context context) {
        super(context, TABLE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public void addPost(dbPost post) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, post.getUsername());
        values.put(TEXT, post.getText());
        values.put(PROFILE_URL, post.getUserImgUrl());
        values.put(POST_URL, post.getPostImgUrl());

        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public List<dbPost> getSavedPosts() {
        List<dbPost> postList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    do {
                        dbPost post = new dbPost(
                                cursor.getString(cursor.getColumnIndex(USER_NAME)),
                                cursor.getString(cursor.getColumnIndex(TEXT)),
                                cursor.getString(cursor.getColumnIndex(PROFILE_URL)),
                                cursor.getString(cursor.getColumnIndex(POST_URL))
                        );

                        postList.add(post);

                    } while (cursor.moveToNext());
                }
            }
        }
        return postList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}
