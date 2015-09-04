package com.media2359.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.media2359.example.JobModel;
import com.media2359.workvoice.model.WorkVoiceData;

import java.sql.SQLException;

/**
 * Created by phutang on 12/1/14.
 */
public class ExampleDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "example.sqlite";
    private static final int DATABASE_VERSION = 2;
    Dao<JobModel,Integer> jobModelIntegerDao;

    public ExampleDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    private static ExampleDatabaseHelper helper = null;


    public static synchronized ExampleDatabaseHelper getHelper(Context context) {
        if (helper == null) {
            helper = new ExampleDatabaseHelper(context);
        }
//        usageCounter.incrementAndGet();
        return helper;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, JobModel.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource,JobModel.class,true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }

    public Dao<JobModel, Integer> getJobDao() {
        if(jobModelIntegerDao == null){
            try {
                jobModelIntegerDao = getDao(JobModel.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jobModelIntegerDao;
    }

}