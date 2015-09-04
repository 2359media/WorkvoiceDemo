package com.media2359.example;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by phutang on 8/31/15.
 */
public class JobAdapter extends BaseAdapter {



    List<JobModel> jobModels;
    public JobAdapter(){
        jobModels =new ArrayList<>();
    }

    @Override
    public int getCount() {
        return jobModels.size();
    }

    @Override
    public Object getItem(int i) {
        return jobModels.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        TextView textView  = (TextView) inflater.inflate( android.R.layout.simple_list_item_1,viewGroup,false);
        textView.setText(jobModels.get(i).toString());
        if(jobModels.get(i).getAnswerId().equals("1")){
            textView.setTextColor(Color.GREEN);
        } else {
            textView.setTextColor(Color.RED);
        }
        return textView;
    }

    public void addData(List<JobModel> data){
        if(data!=null && data.isEmpty() == false){
            jobModels.addAll(data);
            notifyDataSetChanged();
        }
    }
}
