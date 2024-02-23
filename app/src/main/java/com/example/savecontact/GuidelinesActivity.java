package com.example.savecontact;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class GuidelinesActivity extends AppCompatActivity {

    TextView tv_guide;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidelines);

        tv_guide = (TextView) findViewById(R.id.tv_guide);

        tv_guide.setText("1. \"Save this contact\" use this command for save the contact\n" +
                "2. \"India\",\"USA\" use this for selecting Country code\n" +
                "3. \"Call now\" use this for to make a call\n" +
                "4. \"Message now\" use this for navigating message\"");
    }
}