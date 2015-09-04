package com.media2359.sender;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onSendClick(View v){
        WorkvoiceModel workvoiceModel =new WorkvoiceModel();
        workvoiceModel.setAppId("phu tang");
        workvoiceModel.setAutorepeat(false);
        workvoiceModel.setId("1");
        workvoiceModel.setMessage("Command: clean the floor");
        workvoiceModel.setTimeout(5000);

        List<CommandModel> commandModels =new ArrayList<>();
        commandModels.add(new CommandModel("ok","1",false));
        commandModels.add(new CommandModel("reject","2",false));
        workvoiceModel.setAnswerlist(commandModels);
        new RequestData().execute(workvoiceModel);

    }

    public void onsendDataClick(View v){
        EditText sender= (EditText) findViewById(R.id.editText4);
        EditText message= (EditText) findViewById(R.id.editText);
        EditText command1 = (EditText) findViewById(R.id.editText2);
        EditText command2 = (EditText) findViewById(R.id.editText3);
        String sSender = sender.getText().toString().trim();
        String sMessage = message.getText().toString().trim();
        String sCommand1 = command1.getText().toString().trim();
        String sCommand2 = command2.getText().toString().trim();
        if(TextUtils.isEmpty(sSender) || TextUtils.isEmpty(sMessage)|| TextUtils.isEmpty(sCommand1)|| TextUtils.isEmpty(sCommand2)){
            Toast.makeText(this,"please fill all fields",Toast.LENGTH_SHORT).show();
            return;
        }

        WorkvoiceModel workvoiceModel =new WorkvoiceModel();
        workvoiceModel.setAppId(sSender);
        workvoiceModel.setAutorepeat(false);
        workvoiceModel.setId("1");
        workvoiceModel.setMessage(sMessage);
        workvoiceModel.setTimeout(5000);

        List<CommandModel> commandModels =new ArrayList<>();
        commandModels.add(new CommandModel(sCommand1,"1",false));
        commandModels.add(new CommandModel(sCommand2,"2",false));
        workvoiceModel.setAnswerlist(commandModels);
        new RequestData().execute(workvoiceModel);
    }



    public static class RequestData extends AsyncTask<WorkvoiceModel,Void,Void>{
        @Override
        protected Void doInBackground(WorkvoiceModel... workvoiceModels) {
            WorkvoiceModel workvoiceModel =workvoiceModels[0];
            try {
                // Prepare JSON containing the GCM message content. What to send and where to send.
                JSONObject jGcmData = new JSONObject();
                JSONObject jData = new JSONObject();

                jData.put("message", new Gson().toJson(workvoiceModel, WorkvoiceModel.class));
                // Where to send GCM message.
                    jGcmData.put("to", "/topics/global");
                // What to send in GCM message.
                jGcmData.put("data", jData);

                // Create connection to send GCM Message request.
                URL url = new URL("https://android.googleapis.com/gcm/send");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Authorization", "key=AIzaSyD91ezXnsZVGNX8F1l4kx8PRdg1C6h7z9k");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                // Send GCM message content.
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(jGcmData.toString().getBytes());

                // Read GCM response.
                InputStream inputStream = conn.getInputStream();
                String resp = IOUtils.toString(inputStream);
                System.out.println(resp);
                System.out.println("Check your device/emulator for notification or logcat for " +
                        "confirmation of the receipt of the GCM message.");
            } catch (Exception e) {
                System.out.println("Unable to send GCM message.");
                System.out.println("Please ensure that API_KEY has been replaced by the server " +
                        "API key, and that the device's registration token is correct (if specified).");
                e.printStackTrace();
            }


            return null;
        }
    }


}
