package com.example.friendgreet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    EditText nm;
    Button gt;
    // Calendar c=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.msg);
        nm=findViewById(R.id.nm);
        gt=findViewById(R.id.gt);
        nm.setOnClickListener(this);
        gt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView tm  = (TextView) findViewById(R.id.msg);
        EditText efn  = (EditText) findViewById(R.id.nm);
        String friendName = efn .getText().toString();
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        String greeting = null;
        if(hour>=6 && hour<12){
            greeting = "Good Morning";
        } else if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24) {
            greeting = "Good Night";
        }
        switch(v.getId()){
            case R.id.gt:
                tm.setText(greeting+" "+friendName+"!");
                break;
            default:
                break;
        }

    }
}