package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout loTimer, loRange;
    TextView tvSB, tvTimer, tvPrev, tvSBDiff;
    EditText etRandLimit;
    RadioGroup calRG;
    RadioButton RBcal;
    SeekBar rSB, tSB, dSB;
    Button conBtn;

    String mode, diff, rbTextCal, prevCategory, prevTime, prevCompletion, timer, rbCalText, selRange, selMode, sTime;
    Integer cal, prevScore, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        final SharedPreferences getLatestSP = getSharedPreferences("PrevScore", MODE_PRIVATE);

        Intent intentReceived = getIntent();
        mode = intentReceived.getStringExtra("mode");

        loTimer = findViewById(R.id.layoutTimer);
        loRange = findViewById(R.id.layoutRange);
        tvPrev = findViewById(R.id.tvPastScore);
        tvTimer = findViewById(R.id.tvTimer);
        tvSBDiff = findViewById(R.id.tvSBDiff);
        conBtn = findViewById(R.id.btnCon);
        tvSB = findViewById(R.id.sbText);
        calRG = findViewById(R.id.RBcal);
        rSB = findViewById(R.id.sbRange);
        tSB = findViewById(R.id.sbTimer);
        dSB = findViewById(R.id.sbDiff);
        etRandLimit = findViewById(R.id.etRandLimit);

        selMode = getLatestSP.getString("mode", "");
        prevCategory = getLatestSP.getString("category", "");
        prevScore = getLatestSP.getInt("score", 0);
        prevTime = getLatestSP.getString("time", "");
        prevCompletion = getLatestSP.getString("completion", "");

        if (prevTime.equals("") && prevCategory.equals("") && prevScore == 0){
            tvPrev.setText("Previous Score:\nNo Data Detected Recently");
        }else{
            tvPrev.setText("Previous Score ( \" + selMode + \" ) :\nCategory: " + prevCategory + "\nScore: " + prevScore + "/10 ( " + prevCompletion + " )\nTime Left: " + prevTime);
        }

        timer = "0";

        rSB.refreshDrawableState();
        tSB.refreshDrawableState();
        dSB.refreshDrawableState();
        tSB.setMax(30);

        dSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (i == 0){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(10);
                    }
                    diff = "Beginner";
                    tvSBDiff.setText("Beginner");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 20 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 1){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(15);
                    }
                    diff = "Intermediate";
                    tvSBDiff.setText("Intermediate");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 2){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(20);
                    }
                    diff = "Advanced";
                    tvSBDiff.setText("Advanced");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 3){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.GONE);
                        tvSB.setVisibility(View.GONE);
                        etRandLimit.setVisibility(View.VISIBLE);
                        rSB.setMax(100);
                    }
                    diff = "Random";
                    tvSBDiff.setText("Random");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Normal")){
                        Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Competitive")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        tSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                time = i;
                timer = String.valueOf(time);
                tvTimer.setText(String.valueOf(i) + " Mins");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (time == 0){
                    Toast.makeText(MainActivity.this, "TIme cannot be 0 Minutes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Time is set as " + time + " Minutes", Toast.LENGTH_SHORT).show();

                }

            }
        });

        rSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int sbValue;

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                tvSB.setText(String.valueOf(i));
                sbValue = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (sbValue == 0){
                    Toast.makeText(MainActivity.this, "Range cannot be set from 0 to 0", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Range is set as 0 - " + sbValue, Toast.LENGTH_SHORT).show();

                }

            }
        });

        diff = tvSBDiff.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        if (mode.equals("Competitive")){

            loTimer.setVisibility(View.GONE);
            loRange.setVisibility(View.GONE);

            if(diff.equals("Beginner")){
                timer = "0";
                selRange = "10";
            }else if(diff.equals("Intermediate")){
                timer = "0";
                selRange = "15";
            }else if(diff.equals("Advanced")){
                timer = "0";
                selRange = "20";
            }else if(diff.equals("Random")){
                timer = "1200000";
                selRange = "100";
            }

        }else{

            if(diff.equals("Beginner")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(10);
            }else if(diff.equals("Intermediate")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(15);
            }else if(diff.equals("Advanced")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(20);
            }else if(diff.equals("Random")){
                rSB.setVisibility(View.GONE);
                tvSB.setVisibility(View.GONE);
                etRandLimit.setVisibility(View.VISIBLE);
            }

        }

        if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
            Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
        }else if(diff.equals("Beginner")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Intermediate")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Advanced")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }else{

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                diff = tvSBDiff.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
                    Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
                }else if(diff.equals("Beginner")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Intermediate")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Advanced")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mode.equals("Normal") && etRandLimit.getVisibility() == View.VISIBLE && etRandLimit.getText().toString().equals("")){

                    Toast.makeText(MainActivity.this, "Please fill in the Range to continue", Toast.LENGTH_SHORT).show();

                }else if (mode.equals("Normal") && tvSB.getVisibility() == View.VISIBLE && tvSB.getText().toString().equals("0")){

                    Toast.makeText(MainActivity.this, "Range cannot be from 0 - 0\nYou are not that dumb  -_-", Toast.LENGTH_SHORT).show();

                }else{

                    Intent intent = new Intent(getBaseContext(), SecondActivity.class);

                    cal = calRG.getCheckedRadioButtonId();
                    RBcal = findViewById(cal);
                    rbCalText = RBcal.getText().toString();

                    if (mode.equals("Normal")){
                        if (etRandLimit.getVisibility() == View.VISIBLE){
                            selRange = etRandLimit.getText().toString();
                        }else{
                            selRange = tvSB.getText().toString();
                        }
                    }

                    if (!timer.equals("")){
                        sTime = timer;
                    }else{
                        sTime = "0";
                    }

                    intent.putExtra("mode", mode);
                    intent.putExtra("level", diff);
                    intent.putExtra("Calcu", rbCalText);
                    intent.putExtra("max", selRange);
                    intent.putExtra("timer", sTime);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences getLatestSP = getSharedPreferences("PrevScore", MODE_PRIVATE);

        selMode = getLatestSP.getString("mode", "");
        prevCategory = getLatestSP.getString("category", "");
        prevScore = getLatestSP.getInt("score", 0);
        prevTime = getLatestSP.getString("time", "");
        prevCompletion = getLatestSP.getString("completion", "");

        if (prevTime.equals("") && prevCategory.equals("") && prevScore == 0){
            tvPrev.setText("Previous Score:\nNo Data Detected Recently");
        }else{
            tvPrev.setText("Previous Score ( " + selMode + " ) :\nCategory: " + prevCategory + "\nScore: " + prevScore + "/10 ( " + prevCompletion + " )\nTime Left: " + prevTime);
        }

        dSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (i == 0){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(10);
                    }
                    diff = "Beginner";
                    tvSBDiff.setText("Beginner");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 20 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 1){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(15);
                    }
                    diff = "Intermediate";
                    tvSBDiff.setText("Intermediate");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 2){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(20);
                    }
                    diff = "Advanced";
                    tvSBDiff.setText("Advanced");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 3){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.GONE);
                        tvSB.setVisibility(View.GONE);
                        etRandLimit.setVisibility(View.VISIBLE);
                        rSB.setMax(100);
                    }
                    diff = "Random";
                    tvSBDiff.setText("Random");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Normal")){
                        Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Competitive")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        tSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                time = i;
                timer = String.valueOf(time);
                tvTimer.setText(String.valueOf(i) + " Mins");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (time == 0){
                    Toast.makeText(MainActivity.this, "TIme cannot be 0 Minutes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Time is set as " + time + " Minutes", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        if(diff.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(diff.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(diff.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(diff.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
            Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
        }else if(diff.equals("Beginner")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Intermediate")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Advanced")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }else{

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                diff = tvSBDiff.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
                    Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
                }else if(diff.equals("Beginner")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Intermediate")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Advanced")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences getLatestSP = getSharedPreferences("PrevScore", MODE_PRIVATE);

        selMode = getLatestSP.getString("mode", "");
        prevCategory = getLatestSP.getString("category", "");
        prevScore = getLatestSP.getInt("score", 0);
        prevTime = getLatestSP.getString("time", "");
        prevCompletion = getLatestSP.getString("completion", "");

        if (prevTime.equals("") && prevCategory.equals("") && prevScore == 0){
            tvPrev.setText("Previous Score:\nNo Data Detected Recently");
        }else{
            tvPrev.setText("Previous Score ( " + selMode + " ) :\nCategory: " + prevCategory + "\nScore: " + prevScore + "/10 ( " + prevCompletion + " )\nTime Left: " + prevTime);
        }

        dSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (i == 0){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(10);
                    }
                    diff = "Beginner";
                    tvSBDiff.setText("Beginner");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 20 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 1){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(15);
                    }
                    diff = "Intermediate";
                    tvSBDiff.setText("Intermediate");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 15 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 2){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.VISIBLE);
                        tvSB.setVisibility(View.VISIBLE);
                        etRandLimit.setVisibility(View.GONE);
                        rSB.setMax(20);
                    }
                    diff = "Advanced";
                    tvSBDiff.setText("Advanced");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division") || rbTextCal.equals("Random")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }else if(i == 3){
                    if(mode.equals("Normal")){
                        rSB.setVisibility(View.GONE);
                        tvSB.setVisibility(View.GONE);
                        etRandLimit.setVisibility(View.VISIBLE);
                        rSB.setMax(100);
                    }
                    diff = "Random";
                    tvSBDiff.setText("Random");
                    if(rbTextCal.equals("Addition") || rbTextCal.equals("Subtraction")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 10 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Multiplication") || rbTextCal.equals("Division")) {
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Normal")){
                        Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                    }else if(rbTextCal.equals("Random") && mode.equals("Competitive")){
                        Toast.makeText(MainActivity.this, "TIme cannot be 5 Minutes", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        tSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                time = i;
                timer = String.valueOf(time);
                tvTimer.setText(String.valueOf(i) + " Mins");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (time == 0){
                    Toast.makeText(MainActivity.this, "TIme cannot be 0 Minutes", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Time is set as " + time + " Minutes", Toast.LENGTH_SHORT).show();

                }

            }
        });

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        if(diff.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(diff.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(diff.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(diff.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
            Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
        }else if(diff.equals("Beginner")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Intermediate")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            }

        }else if(diff.equals("Advanced")){

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Random")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }else{

            if (rbTextCal.equals("Addition")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Subtraction")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Multiplication")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            } else if (rbTextCal.equals("Division")) {

                Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

            }

        }

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                diff = tvSBDiff.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Normal")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(diff.equals("Random") && rbTextCal.equals("Random") && mode.equals("Competitive")) {
                    Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();
                }else if(diff.equals("Beginner")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 20 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Intermediate")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 15 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    }

                }else if(diff.equals("Advanced")){

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Random")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }else{

                    if (rbTextCal.equals("Addition")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Subtraction")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 10 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Multiplication")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    } else if (rbTextCal.equals("Division")) {

                        Toast.makeText(MainActivity.this, "Default timer is set to 5 Min", Toast.LENGTH_SHORT).show();

                    }

                }

            }
        });

    }

}
