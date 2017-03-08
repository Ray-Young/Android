package com.example.lei.hangmangame;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String[][] puzzles = {{"STEAK", "TOAST", "BREAD", "CHIPS"}, {"ALICE", "CLARE", "SIMON", "PARIS"}};
    private static String[] hints = {"FOOD", "NAME"};
    private static String[] dict = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] words;
    private String hint;
    private ImageView imv;
    private TextView tvHint;
    private Button btnNewGame;


    private String thisWord;
    private Random rd;
    private int count = 6;
    private int correct = 0;
    private char[] btnChars;
    private char[] txtChars;
    private Button[] set;
    private EditText[] txtSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayout();
        initializeGame();
        updateOperation();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);

        initializeLayout();
        updateHangman();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvHint.setText(hint);
        }
        for (int i = 0; i < btnChars.length; i++) {
            set[i].setText(String.valueOf(btnChars[i]));
        }

        for (int i = 0; i < txtChars.length; i++) {
            txtSet[i].setText(String.valueOf(txtChars[i]));
        }

        updateOperation();
    }

    public void initializeLayout() {
        set = new Button[12];
        txtSet = new EditText[5];
        imv = (ImageView) findViewById(R.id.imv);
        tvHint = (TextView) findViewById(R.id.hint);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        set[0] = (Button) findViewById(R.id.b1);
        set[1] = (Button) findViewById(R.id.b2);
        set[2] = (Button) findViewById(R.id.b3);
        set[3] = (Button) findViewById(R.id.b4);
        set[4] = (Button) findViewById(R.id.b5);
        set[5] = (Button) findViewById(R.id.b6);
        set[6] = (Button) findViewById(R.id.b7);
        set[7] = (Button) findViewById(R.id.b8);
        set[8] = (Button) findViewById(R.id.b9);
        set[9] = (Button) findViewById(R.id.b10);
        set[10] = (Button) findViewById(R.id.b11);
        set[11] = (Button) findViewById(R.id.b12);
        txtSet[0] = (EditText) findViewById(R.id.l1);
        txtSet[1] = (EditText) findViewById(R.id.l2);
        txtSet[2] = (EditText) findViewById(R.id.l3);
        txtSet[3] = (EditText) findViewById(R.id.l4);
        txtSet[4] = (EditText) findViewById(R.id.l5);

        btnNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }

    public void initializeGame() {
        btnChars = new char[12];
        txtChars = new char[5];
        rd = new Random();
        int x = rd.nextInt(puzzles.length);
        hint = hints[x];
        words = puzzles[x];
        x = rd.nextInt(puzzles[0].length);
        System.out.println(hint);

        thisWord = words[x];
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvHint.setText(hint);
        }

        for (int i = 0; i < thisWord.length(); i++) {
            char c = thisWord.charAt(i);
            int m = (int) (Math.random() * 12);
            while (m >= 0) {
                if (set[m].getText().length() < 1) {
                    set[m].setText(Character.toString(c));
                    btnChars[m] = c;
                    break;
                }
                m = (int) (Math.random() * 12);
            }
        }


        for (int i = 0; i < set.length; i++) {
            if (set[i].getText().length() < 1) {
                int m = (int) (Math.random() * 26);
                while (thisWord.contains(dict[m])) {
                    m = (int) (Math.random() * 26);
                }
                set[i].setText(dict[m]);
                btnChars[i] = dict[m].charAt(0);
            }
        }

        System.out.println(thisWord + "--------------------------------------");
    }

    public void updateHangman() {
        if (count == 5)
            imv.setImageResource(R.drawable.hangman1);
        else if (count == 4)
            imv.setImageResource(R.drawable.hangman2);
        else if (count == 3)
            imv.setImageResource(R.drawable.hangman3);
        else if (count == 2)
            imv.setImageResource(R.drawable.hangman4);
        else if (count == 1)
            imv.setImageResource(R.drawable.hangman5);
        else if (count == 0) {
            imv.setImageResource(R.drawable.hangman6);
            gameEnd(set);
        }
    }

    public void updateOperation() {
        for (int i = 0; i < set.length; i++) {
            final Button btn = set[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = (String) btn.getText();
                    int n = thisWord.indexOf(s.charAt(0));
                    if (n != -1) {
                        txtSet[n].setText(s);
                        txtChars[n] = s.charAt(0);
                        thisWord = thisWord.replace(s.charAt(0), '~');
                        correct++;
                        System.out.println(thisWord + "---------------------------------");
                        if (correct == thisWord.length()) {
                            imv.setImageResource(R.drawable.hangmanwin);
                            gameEnd(set);
                        }
                    } else {
                        count--;
                        updateHangman();
                    }

                    disabledButton(btnChars, btn);
                    btn.setClickable(false);
                    btn.setText("");
                }
            });
        }
    }


    public void gameEnd(Button[] set) {
        for (int i = 0; i < set.length; i++) {
            set[i].setClickable(false);
        }
    }

    public void disabledButton(char[] btnChars, Button btn) {
        char btnChar = btn.getText().toString().charAt(0);
        for (int i = 0; i < btnChars.length; i++) {
            if (btnChar == btnChars[i]) {
                btnChars[i] = ' ';
                break;
            }
        }
    }
}
