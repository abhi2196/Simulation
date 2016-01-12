package com.example.abhi.simulation;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Abhi on 10-Jan-16.
 */
public class CustomAdapter extends BaseAdapter {
    Context c;
    List<Row> row;

    CustomAdapter (Context c,List<Row> row){
        this.c=c;
        this.row=row;
    }

    @Override
    public int getCount() {
        return row.size();
    }

    @Override
    public Object getItem(int position) {
        return row.get(position);
    }

    @Override
    public long getItemId(int position) {
        return row.indexOf(row.get(position));
    }

    private class ViewHolder{
        ImageView pro_pic;
        TextView status;
        TextView name;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v=null;
        LayoutInflater m=(LayoutInflater)c.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        v = new ViewHolder();
        if(convertView==null){
            convertView = m.inflate(R.layout.list_item,null);
            v.name=(TextView)convertView.findViewById(R.id.member_name);
            v.pro_pic=(ImageView)convertView.findViewById(R.id.profile_pic);
            v.status=(TextView)convertView.findViewById(R.id.status);
            convertView.setTag(v);
        }
        else
        {
            v=(ViewHolder)convertView.getTag();
        }
        Row pos = row.get(position);
        v.pro_pic.setImageResource(pos.getPic_id());
        v.name.setText(pos.getName());
        v.status.setText(pos.getStatus());
        return convertView;
    }
}
