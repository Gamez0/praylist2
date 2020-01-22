package com.rozdoum.socialcomponents.main.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.rozdoum.socialcomponents.R;

public class PrayerForActivity extends AppCompatActivity {
    protected Button forJesusButton;
    protected Button forOthersButton;
    protected Button forYouButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_for);

        forJesusButton = (Button) findViewById(R.id.forJesus);
        forOthersButton  = (Button) findViewById(R.id.forOthers);
        forYouButton = (Button) findViewById(R.id.forYou);

        Intent intent = new Intent();

        forJesusButton.setOnClickListener(v->{
            intent.putExtra("prayerFor",0);
            setResult(0);
            finish();
        });

        forOthersButton.setOnClickListener(v->{
            intent.putExtra("prayerFor",2);
            setResult(1);
            finish();
        });

        forYouButton.setOnClickListener(v->{
            intent.putExtra("prayerFor",2);
            setResult(2);
            finish();
        });

    }
}
