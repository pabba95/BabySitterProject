package com.example.pabba.babysitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BabySitterActivity extends AppCompatActivity  {

    private Button calculate;
    private EditText startTime;
    private EditText endTime;
    private static final String BED_TIME = "8:00 P.M";
    private static final String MID_NIGHT = "12:00 A.M";
    private static final String START_TIME = "5:00 P.M";
    private static final String END_TIME = "4:00 A.M";

    private static final String HOURS = "hours";
    private static final String BABYHOURS = "babySittertime";
    private static final String AMOUNT = "amounttoBePayed";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent babyResult = new Intent(BabySitterActivity.this,BabysitterCalResults.class);
        final Bundle bundle = new Bundle();

        calculate = (Button)findViewById(R.id.button);
        startTime = (EditText)findViewById(R.id.startTime);
        endTime = (EditText)findViewById(R.id.endTime);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Date sTimeDT = null;
                Date eTimeDT = null;
                String sTime = startTime.getText().toString();
                String eTime = endTime.getText().toString();


                sTimeDT = dateFormatterToString(sTime);
                eTimeDT = dateFormatterToString(eTime);

                if (sTimeDT != null && (sTimeDT.compareTo(dateFormatterToString(START_TIME)) >= 0) && eTime != null && (eTimeDT.compareTo(dateFormatterToString(BED_TIME)) <= 0)) {

                    int hours = calTime(sTime, eTime);
                    int amounttoBePayed = hours * 12;
                    bundle.putString(BABYHOURS, START_TIME + " " + BED_TIME);
                    bundle.putString(HOURS, String.valueOf(hours));
                    bundle.putString(AMOUNT, String.valueOf(amounttoBePayed));
                    babyResult.putExtras(bundle);


                }

                if (sTimeDT != null && (sTimeDT.compareTo(dateFormatterToString(BED_TIME)) >= 0) && eTime != null && (eTimeDT.compareTo(dateFormatterToString(MID_NIGHT)) <= 0)) {

                    int hours = calTime(sTime, eTime);
                    int amounttoBePayed = hours * 8;
                    bundle.putString(BABYHOURS, BED_TIME + " " + MID_NIGHT);
                    bundle.putString(HOURS, String.valueOf(hours));
                    bundle.putString(AMOUNT, String.valueOf(amounttoBePayed));
                    babyResult.putExtras(bundle);


                }

                if (sTimeDT != null && (sTimeDT.compareTo(dateFormatterToString(MID_NIGHT)) >= 0) && eTime != null && (eTimeDT.compareTo(dateFormatterToString(END_TIME)) <= 0)) {

                    int hours = calTime(sTime, eTime);
                    int amounttoBePayed = hours * 16;
                    bundle.putString(BABYHOURS, MID_NIGHT + " " + END_TIME);
                    bundle.putString(HOURS, String.valueOf(hours));
                    bundle.putString(AMOUNT, String.valueOf(amounttoBePayed));
                    babyResult.putExtras(bundle);


                }

                if (sTimeDT != null && (sTimeDT.compareTo(dateFormatterToString(START_TIME)) >= 0) && eTime != null && (eTimeDT.compareTo(dateFormatterToString(END_TIME)) <= 0)) {

                    int hours = calTime(sTime, eTime);
                    int amounttoBePayed = (hours * 16) + (hours * 3) + (hours * 12);
                    bundle.putString(BABYHOURS, START_TIME + " to " + END_TIME);
                    bundle.putString(HOURS, String.valueOf(hours));
                    bundle.putString(AMOUNT, String.valueOf(amounttoBePayed));
                    babyResult.putExtras(bundle);


                }

                startActivity(babyResult);

            }


        });

    }


    private Date dateFormatterToString(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        try {

            Calendar cal = Calendar.getInstance();
            cal.setTime(simpleDateFormat.parse(date));
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            String time  = hour + ":" + minute;
             return simpleDateFormat.parse(time);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }


    private int calTime(String startTime, String endTime){
        int hours = 0;
        try {


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
           Date date1 = simpleDateFormat.parse(startTime);
           Date date2 = simpleDateFormat.parse(endTime);

            long difference = date2.getTime() - date1.getTime();
           int days = (int) (difference / (1000 * 60 * 60 * 24));
            hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        }
        catch (Exception ex){
             ex.printStackTrace();
        }
        return hours;
    }


}
