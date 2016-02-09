package com.example.pabba.babysitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BabysitterCalResults extends AppCompatActivity {

    private TextView babySittersTime;
    private TextView babySittersHours;
    private TextView babySittersAmount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babbysitter_cal_results);
        Bundle results = getIntent().getExtras();
        babySittersTime = (TextView)findViewById(R.id.babyTimeCalc);
        babySittersHours = (TextView)findViewById(R.id.babyTimeHoursCalc);
        babySittersAmount = (TextView)findViewById(R.id.amounttoBePaidTxt);

        babySittersTime.setText(results.getString("babySittertime"));
        babySittersHours.setText(results.getString("hours"));
        babySittersAmount.setText(results.getString("amounttoBePayed"));

    }

}
