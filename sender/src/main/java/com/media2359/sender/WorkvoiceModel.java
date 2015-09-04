package com.media2359.sender;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model of workvoice, include the answers list and the response to server.
 * Created by phutang on 8/14/15.
 */
public class WorkvoiceModel {
//    {
//        "appId":"AppId",
//            "id":"Command_id",
//            "Message":"Hey I need to do something",
//            "RequireToReply":true,
//            "AnswerList":[
//        {
//            "Command":"Answer sentence X",
//                "answerdId":"id-1",
//                "default":true
//        },
//        {
//            "Command":"Answer sentence Y",
//                "answerdId":"id-2",
//                "default":false
//        }
//        ],
//        "AutoRepeat":true,
//            "timeout":5000
//    }
    @SerializedName("appId")
    String appId;

    @SerializedName("id")
    String id;

    @SerializedName("Message")
    String message;

    @SerializedName("RequireToReply")
    boolean requireToReply;

    @SerializedName("AnswerList")
    List<CommandModel> answerlist;

    @SerializedName("AutoRepeat")
    boolean autorepeat;

    @SerializedName("timeout")
    long timeout;


    public String getTTS_String(){
        String ttsString= "Message from "+appId+ " : "+message+". There are "+answerlist.size();
        for (int i = 1; i <= answerlist.size() ; i++) {
            ttsString= ttsString+" command "+i+" : "+ answerlist.get(i);
        }
        return ttsString;
    }

    public String getAppId() {
        return appId;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRequireToReply() {
        return requireToReply;
    }

    public List<CommandModel> getAnswerlist() {
        return answerlist;
    }

    public boolean isAutorepeat() {
        return autorepeat;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRequireToReply(boolean requireToReply) {
        this.requireToReply = requireToReply;
    }

    public void setAnswerlist(List<CommandModel> answerlist) {
        this.answerlist = answerlist;
    }

    public void setAutorepeat(boolean autorepeat) {
        this.autorepeat = autorepeat;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

}
