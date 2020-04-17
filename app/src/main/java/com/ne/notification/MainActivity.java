package com.ne.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText hour,minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void notification(View view) {
        Calendar calendar=Calendar.getInstance();

        hour=findViewById(R.id.et_hour);
        minute=findViewById(R.id.et_minute);

        String s1= hour.getText().toString();
        String s2= minute.getText().toString();

        //calendar.set(2019, 6, 24);
        calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(s1));
        calendar.set(Calendar.MINUTE,Integer.parseInt(s2));
        //calendar.set(Calendar.SECOND,5);
        Toast.makeText(this, "Notification Created!!", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),notification_receiver.class);
        intent.putExtra("h",s1);
        intent.putExtra("m",s2);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY*30,pendingIntent);
    }
}
