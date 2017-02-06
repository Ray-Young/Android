package com.example.lei.hw2_22;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtNumber1;
    private EditText edtNumber2;
    private TextView tvResult;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
        tvResult = (TextView) findViewById(R.id.tvResult);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = Integer.parseInt(edtNumber1.getText().toString()) + Integer.parseInt(edtNumber2.getText().toString());
                tvResult.setText(String.valueOf(result));
            }
        });
    }
}
