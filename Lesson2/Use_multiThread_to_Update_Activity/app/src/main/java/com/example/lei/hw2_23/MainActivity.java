package com.example.lei.hw2_23;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private TextView tvLight;
    private static int limitExcuteTime = 89;
    private static String logFilterFlag = "red";
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = (Button) findViewById(R.id.btnStart);
        tvLight = (TextView) findViewById(R.id.tvLight);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread() {
                    @Override
                    public void run() {
                        try {
                            while (!isInterrupted()) {
                                Thread.sleep(3000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        simulate(i);
                                        i++;
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                        }
                    }
                };
                t.start();
            }
        });
    }

    public void simulate(int i) {
        if (i < limitExcuteTime) {
            try {
                Log.i(logFilterFlag, "aaa: " + String.valueOf(i % 3));
                if (i % 3 == 0) {
                    tvLight.setText("Red Light, Stop");
                    tvLight.setBackgroundColor(Color.RED);
                } else if (i % 3 == 1) {
                    tvLight.setText("Yellow Light, Wait");
                    tvLight.setBackgroundColor(Color.YELLOW);
                } else if (i % 3 == 2) {
                    tvLight.setText("Green Light, Go");
                    tvLight.setBackgroundColor(Color.GREEN);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
