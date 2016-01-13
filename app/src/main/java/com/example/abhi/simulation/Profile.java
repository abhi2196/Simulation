package com.example.abhi.simulation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity{
    private String Name;
    private int picid;
    private String Status;
    private long i;
    private TextView on1;
    private TextView on2;
    private ImageView im;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        Name = extras.getString("Name");
        picid = extras.getInt("Id1");
        Status = extras.getString("Status");
        i=extras.getLong("Id");
        setup();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mail me, abhicool673@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void setup(){
        on1= (TextView)findViewById(R.id.t1);
        on1.setText(Name);
        on2 = (TextView)findViewById(R.id.t2);
        on2.setText(Status);
        im = (ImageView)findViewById(R.id.header_imageview);
        im.setImageResource(picid);
        Toast.makeText(Profile.this,"Id"+ i,Toast.LENGTH_LONG).show();
    }

}
