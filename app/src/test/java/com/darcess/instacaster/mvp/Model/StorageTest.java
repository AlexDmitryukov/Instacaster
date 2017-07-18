package com.darcess.instacaster.mvp.Model;

import android.database.sqlite.SQLiteDatabase;

import com.darcess.instacaster.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static com.darcess.instacaster.util.Global.CREATE_TABLE;
import static com.darcess.instacaster.util.Global.DROP_TABLE;
import static org.junit.Assert.*;

/**
 * Created by Alexander Dmitryukov on 7/18/2017.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, packageName = "com.darcess.instacaster")
public class StorageTest {

    Storage mStorage;
    SQLiteDatabase mDatabase;

    String username = "test_user";
    String text = "test_text";
    String profile_url = "test_profile_url";
    String post_url = "test_post_url";

    dbPost post;

    @Before
    public void setUp() throws Exception {
        mStorage = new Storage(RuntimeEnvironment.application);
        post = new dbPost(username,text,profile_url,post_url);
        mDatabase = mStorage.getReadableDatabase();
    }

    @After
    public void tearDown() throws Exception {
        mStorage.close();
    }

    @Test
    public void onCreate() throws Exception {
        createTable();
        assertTrue(mDatabase.isOpen());
    }

    @Test
    public void addPost() throws Exception {
        addPost(post);
        assertEquals((mStorage.getSavedPosts()).get(0).getUsername(), username);
        assertEquals((mStorage.getSavedPosts()).get(0).getText(), text);
        assertEquals((mStorage.getSavedPosts()).get(0).getUserImgUrl(), profile_url);
        assertEquals((mStorage.getSavedPosts()).get(0).getPostImgUrl(), post_url);
    }

    @Test
    public void getSavedPosts() throws Exception {
        addPost(post);
        assertTrue(!mStorage.getSavedPosts().isEmpty());
    }

    @Test
    public void onUpgrade() throws Exception {
        createTable();
        addPost(post);
        createTable();
        assertTrue(mStorage.getSavedPosts().isEmpty());
    }

    public void addPost(dbPost post){
        mStorage.addPost(post);
    }

    public void createTable(){
        mDatabase = mStorage.getReadableDatabase();
        mDatabase.execSQL(DROP_TABLE);
        mDatabase.execSQL(CREATE_TABLE);
    }

    public void dropTable(){
        mDatabase.execSQL(DROP_TABLE);
    }

}