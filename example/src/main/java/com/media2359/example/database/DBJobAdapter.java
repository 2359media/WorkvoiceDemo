package com.media2359.example.database;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.media2359.example.JobModel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by phutang on 12/1/14.
 */
public class DBJobAdapter {


    private ExampleDatabaseHelper db;
    Dao<JobModel, Integer> categoryDao;

    public DBJobAdapter(Context context){
            db = ExampleDatabaseHelper.getHelper(context);
            categoryDao = db.getJobDao();
    }

    public JobModel inserRecord(JobModel model){
        try {
              return categoryDao.createIfNotExists(model);
        } catch (SQLException e) {
//            e.printStackTrace();
            return null;
        }
    }

    public JobModel update(JobModel model){
        try {
            categoryDao.update(model);
            return model;
        } catch (SQLException e) {
//            e.printStackTrace();
            return null;
        }
    }

    public List<JobModel> getAll(){
        try {
            return  categoryDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
