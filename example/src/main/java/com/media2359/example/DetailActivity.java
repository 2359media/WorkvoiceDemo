package com.media2359.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.media2359.workvoice.WorkVoice;
import com.media2359.workvoice.model.WorkVoiceData;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int jobId = getIntent().getIntExtra("data", 0);
        //You can query to get history workvoice with the given id in on Result in Workvoice listner
        WorkVoiceData workVoice = WorkVoice.getInstance().getWorkvoiceCommand(jobId);

        TextView content = (TextView) findViewById(R.id.contentPush);
        try {
            JSONObject data =new JSONObject(workVoice.getContentData());
            content.setText(data.toString(4));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        TextView asr = (TextView) findViewById(R.id.contentASR);
        asr.setText(workVoice.getAnswer());

        TextView id = (TextView) findViewById(R.id.contentAnswerId);
        id.setText(workVoice.getAnswerId());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
