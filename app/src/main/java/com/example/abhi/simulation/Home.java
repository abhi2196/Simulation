package com.example.abhi.simulation;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener{
    String[] names;
    String name;
    TypedArray pro_pics;
    String[] statues;
    Set<BluetoothDevice> devices;
    BluetoothAdapter blue;
    List<Row> row;
    ListView mylist;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        blue=BluetoothAdapter.getDefaultAdapter();

        if(!blue.isEnabled())
            showAlertDialog(this,"Bluetooth Settings","Turn on your Bluetooth");
        if(i==0)
        setup();
        i++;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Click to Refresh the List", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                setup();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        if(id==R.id.action_refresh){
            setup();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setup(){
        row = new ArrayList<Row>();
        devices=blue.getBondedDevices();
        pro_pics = getResources().obtainTypedArray(R.array.pro_pics);

        statues = getResources().getStringArray(R.array.status);
        int i=0;
        if(devices.size()>0) {
            for (BluetoothDevice d:devices) {
                i=0;
                Row item = new Row(d.getName(),
                        pro_pics.getResourceId(i, -1), statues[i]);
                row.add(item);
            }
            int j=0;
            for(i=0;i<devices.size();i++)
            {
                row.get(i).setPic_id(pro_pics.getResourceId(i,-1));
                row.get(i).setStatus(statues[i]);
            }
        }
        mylist = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this, row);
        mylist.setAdapter(adapter);
        pro_pics.recycle();
        mylist.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Row r=(Row)row.get(position);
        Intent i = new Intent();
        i.setClass(Home.this, Profile.class);
        String str= r.getName();
        String stat =r.getStatus();
        int id1 =r.getPic_id();
        i.putExtra("Name",str);
        i.putExtra("Id",id);
        i.putExtra("Status",stat);
        i.putExtra("Id1",id1);
        startActivity(i);
    }

    public void showAlertDialog(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, 1);
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
