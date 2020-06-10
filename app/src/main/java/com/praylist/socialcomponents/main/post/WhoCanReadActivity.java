package com.praylist.socialcomponents.main.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.praylist.socialcomponents.R;

public class WhoCanReadActivity extends AppCompatActivity {
    protected Button toEveryOne;
//    protected Button onlyFellow;
    protected Button onlyMe;
//    protected Button someFellow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_can_read);

        toEveryOne = (Button) findViewById(R.id.toEveryOne);
//        onlyFellow = (Button) findViewById(R.id.onlyFellow);
        onlyMe = (Button) findViewById(R.id.onlyMe);
//        someFellow = (Button) findViewById(R.id.someFellow);

        Intent intent = new Intent();
        toEveryOne.setOnClickListener(v->{

            intent.putExtra("whoCanRead",0);

            setResult(0);
            finish();
        });
//        onlyFellow.setOnClickListener(v->{
//            intent.putExtra("whoCanRead",1);
//            setResult(1);
//            finish();
//        });
        onlyMe.setOnClickListener(v->{
            intent.putExtra("whoCanRead",2);
            setResult(2);
            finish();
        });
//        someFellow.setOnClickListener(v->{
//            //
//
//            finish();
//        });
    }
}
