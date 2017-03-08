package com.example.lei.hw2_part1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvNumber1;
    private TextView tvNumber2;
    private TextView tvResult;
    private Button btnClickMe;
    private Random rd;
    private int num1;
    private int num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNumber1 = (TextView) findViewById(R.id.tvNumber1);
        tvNumber2 = (TextView) findViewById(R.id.tvNumber2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnClickMe = (Button) findViewById(R.id.btnClickMe);
        rd = new Random();
        num1 = rd.nextInt(10) + 1;
        num2 = rd.nextInt(10) + 1;

        tvNumber1.setText(String.valueOf(num1));
        tvNumber2.setText(String.valueOf(num2));

        btnClickMe.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              tvResult.setText(String.valueOf(num1 + num2));
                                          }
                                      }

        );
    }

}
