package com.media2359.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.media2359.example.database.DBJobAdapter;
import com.media2359.workvoice.WorkVoice;
import com.media2359.workvoice.listener.WorkVoiceListener;
import com.media2359.workvoice.listener.WorkVoiceRegisterListener;
import com.media2359.workvoice.model.WorkvoiceModel;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by phutang on 8/26/15.
 */
public class ExampleActiviy extends Activity {

    ListView lv;
    JobAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init your application/view here.
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MyService.class));
        lv=(ListView) findViewById(R.id.lv);
        DBJobAdapter jobAdapter =new DBJobAdapter(ExampleActiviy.this);
        adapter = new JobAdapter();
        lv.setAdapter(adapter);
        adapter.addData(jobAdapter.getAll());
        //step 1: register with workvoice system.
        String gcmSenderId ="1223805314"; //ask your system admin your gcmSender id, we will use it for register your device with Google cloud message
        String userId =""; //your system uniqueID, it should be user id, It define by your system to query when need to send a command.
        WorkVoice.getInstance().register(gcmSenderId, userId,getApplicationContext(), new WorkVoiceRegisterListener() {
            @Override
            public void onInit() {
                //start register. You can to something like show dialog popup
                Log.d("gcm","init");
            }

            @Override
            public void onProgressUpdate() {
                //TODO we will update later
                Log.d("gcm","update");
            }

            @Override
            public void onComplete(int resultCode) {
                // return the final result of register (register with gcm success or not, register with workvoice sytem or not.
                Log.d("gcm","success="+resultCode);
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JobModel jobModel = (JobModel) lv.getItemAtPosition(i);
                Intent intent = new Intent(ExampleActiviy.this,DetailActivity.class);
                intent.putExtra("data",jobModel.getId());
                startActivity(intent);
            }
        });
    }

    public void onEvent(NewJobEvent event){
        DBJobAdapter jobAdapter =new DBJobAdapter(ExampleActiviy.this);
        adapter.addData(jobAdapter.getAll());
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }


}
