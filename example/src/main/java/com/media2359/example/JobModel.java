package com.media2359.example;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by phutang on 8/28/15.
 */
public class JobModel {
    @DatabaseField(id = true)
    int id;
    @DatabaseField
    String command;

    @DatabaseField
    String answerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public JobModel() {

    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return command;
    }
}

