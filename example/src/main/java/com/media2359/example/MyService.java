package com.media2359.example;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Toast;

import com.media2359.example.database.DBJobAdapter;
import com.media2359.workvoice.WorkVoice;
import com.media2359.workvoice.listener.WorkVoiceListener;
import com.media2359.workvoice.model.WorkvoiceModel;

import de.greenrobot.event.EventBus;

public class MyService extends Service implements WorkVoiceListener {
    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //add listener
        WorkVoice.getInstance().addWorkVoiceListener(this);
    }

    @Override
    public void onStartProgress(WorkvoiceModel model) {
        //trigger when having a new push
    }

    @Override
    public void onResult(WorkvoiceModel model) {
        //implement next step, you will have full reply and push model in here
        DBJobAdapter jobAdapter = new DBJobAdapter(this);
        if (model.getAnswer() != null) {
            JobModel jobModel = new JobModel();
            jobModel.id = model.getWorkvoiceId();
            jobModel.command = model.getMessage();
            jobModel.answerId = model.getAnswer().getAnserId();
            jobAdapter.inserRecord(jobModel);
            Toast.makeText(this, "output=" + model.getAsrOutput() + " matched!", Toast.LENGTH_LONG).show();
//            adapter = new ArrayAdapter<JobModel>(ExampleActiviy.this, android.R.layout.simple_list_item_1, jobAdapter.getAll());
//            lv.setAdapter(adapter);
            EventBus.getDefault().post(new NewJobEvent());
        } else {
            if (!TextUtils.isEmpty(model.getAsrOutput())) {
                Toast.makeText(this, "output=" + model.getAsrOutput() + " not matched!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "don't have output", Toast.LENGTH_LONG).show();

            }
        }

    }

    @Override
    public void onDestroy() {
        //remove listener when class destroy
        WorkVoice.getInstance().unRegister(this);
        super.onDestroy();
    }
}
