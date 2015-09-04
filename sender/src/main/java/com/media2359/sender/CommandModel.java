package com.media2359.sender;

import com.google.gson.annotations.SerializedName;

/**
 * Created by phutang on 8/27/15.
 */
public class CommandModel {
//    {
//            "Command":"Answer sentence X",
//                "answerdId":"id-1",
//                "default":true
//        },

    @SerializedName("Command")
    String command;
    @SerializedName("answerdId")
    String anserId;
    @SerializedName("default")
    boolean isDefault;

    public String getCommand() {
        return command;
    }

    public String getAnserId() {
        return anserId;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public CommandModel() {
    }

    public CommandModel(String command, String anserId, boolean isDefault) {
        this.command = command;
        this.anserId = anserId;
        this.isDefault = isDefault;
    }
}
