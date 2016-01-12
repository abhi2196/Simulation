package com.example.abhi.simulation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Test;

public class Signup extends AppCompatActivity {
    private Button cnf;
    public static final String PREF_NAME = "Abhishek";
    public static final int PRIVATE_MODE = 0;
    public static final String KEY_IS_LOGGED_IN="SignedUp";
    private ProgressDialog dial;
    SharedPreferences.Editor editor;
    private EditText name;
    private EditText age,c6;
    private RadioButton gender1,gender2;
    private CheckBox c1,c2,c3,c4,c5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        cnf=(Button)findViewById(R.id.btnRegister);
        dial = new ProgressDialog(this);
        dial.setCancelable(false);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        gender1=(RadioButton)findViewById(R.id.rBtnUpId);
        gender2=(RadioButton)findViewById(R.id.rBtnDownId);
        c1=(CheckBox)findViewById(R.id.chkSelected);
        c2=(CheckBox)findViewById(R.id.chkSelected2);
        c3=(CheckBox)findViewById(R.id.chkSelected3);
        c4=(CheckBox)findViewById(R.id.chkSelected4);
        c5=(CheckBox)findViewById(R.id.chkSelected5);
        c6=(EditText)findViewById(R.id.tvName6);

        cnf.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View v) {
                                       if (!name.getText().toString().trim().isEmpty() && !age.getText().toString().trim().isEmpty()) {
                                           dial.setMessage("Logging In");
                                           dial.show();
                                           SharedPreferences sp = getApplicationContext().getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                                           SharedPreferences.Editor edit = sp.edit();
                                           edit.putBoolean("completed", true);
                                           edit.commit();
                                           editor = sp.edit();
                                           editor.putBoolean(KEY_IS_LOGGED_IN, true);
                                           editor.commit();
                                           enter();
                                           Intent i = new Intent("Home");
                                           startActivity(i);
                                           finish();
                                       }
                                       else
                                       {
                                           Toast.makeText(Signup.this,"Please enter the credentials!",Toast.LENGTH_LONG).show();
                                       }
                                   }
                               }
        );

    }
    private void enter(){
        DatabaseHandler db = new DatabaseHandler(this);
        TextView t =(TextView)findViewById(R.id.tvName);
        TextView t1 =(TextView)findViewById(R.id.tvName1);
        TextView t2 =(TextView)findViewById(R.id.tvName3);
        TextView t3 =(TextView)findViewById(R.id.tvName4);
        TextView t4 =(TextView)findViewById(R.id.tvName5);
        TextView t5 =(TextView)findViewById(R.id.tvName6);
        Log.d("Inserting Data","");
        String Gender;
        String Interests[]=new String[100];
        int i=0;
        if(gender1.isChecked())
            Gender=gender1.getText().toString();
        else
            Gender=gender2.getText().toString();
        if(c1.isChecked())
            Interests[i++]=t.getText().toString();
        else if(c2.isChecked())
            Interests[i++]=t1.getText().toString();
        else if(c3.isChecked())
            Interests[i++]=t2.getText().toString();
        else if(c4.isChecked())
            Interests[i++]=t3.getText().toString();
        else if(c5.isChecked())
            Interests[i++]=t4.getText().toString();
        Interests[i]=t5.getText().toString();
        db.addData(new Data(name.getText().toString().trim(),Integer.parseInt(age.getText().toString().trim()),Gender,Interests));
    }
}
