package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    TextView tvSB, tvTimer, tvPrev;
    EditText etTimer, etRandLimit;
    RadioGroup lvlRG, calRG;
    RadioButton RBlvl, RBcal;
    SeekBar rSB;
    Button conBtn;

    String mode, rbTextLvl, rbTextCal, prevCategory, prevTime, prevCompletion, timer, rbLvlText, rbCalText, selRange, selMode;
    Integer lvl, cal, prevScore;

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
        tvTimer = findViewById(R.id.tvTime);
        etTimer = findViewById(R.id.etTimer);
        conBtn = findViewById(R.id.btnCon);
        tvSB = findViewById(R.id.sbText);
        lvlRG = findViewById(R.id.RBlvl);
        calRG = findViewById(R.id.RBcal);
        rSB = findViewById(R.id.sbRange);
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

        rSB.refreshDrawableState();

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbTextLvl = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        Log.i("TAG", "onCreate: lmao " + mode);

        if (mode.equals("Competitive")){

            Log.i("TAG", "onCreate: lmao Comp");

            loTimer.setVisibility(View.GONE);
            loRange.setVisibility(View.GONE);

            if(rbTextLvl.equals("Beginner")){
                timer = "0";
                selRange = "10";
            }else if(rbTextLvl.equals("Intermediate")){
                timer = "0";
                selRange = "15";
            }else if(rbTextLvl.equals("Advanced")){
                timer = "0";
                selRange = "20";
            }else if(rbTextLvl.equals("Random")){
                timer = "1200000";
                selRange = "100";
            }

        }else{

            Log.i("TAG", "onCreate: lmao Norm");

            if(rbTextLvl.equals("Beginner")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(10);
            }else if(rbTextLvl.equals("Intermediate")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(15);
            }else if(rbTextLvl.equals("Advanced")){
                rSB.setVisibility(View.VISIBLE);
                tvSB.setVisibility(View.VISIBLE);
                etRandLimit.setVisibility(View.GONE);
                rSB.setMax(20);
            }else if(rbTextLvl.equals("Random")){
                rSB.setVisibility(View.GONE);
                tvSB.setVisibility(View.GONE);
                etRandLimit.setVisibility(View.VISIBLE);
            }

        }

        if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(rbTextLvl.equals("Beginner")){

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

        }else if(rbTextLvl.equals("Intermediate")){

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

        }else if(rbTextLvl.equals("Advanced")){

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

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbTextLvl = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if(rbTextLvl.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbTextLvl.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbTextLvl.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbTextLvl.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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

        etTimer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                Double limitTime;

                try {

                    if (etTimer.getText().toString().equals("")){
                        Toast.makeText(MainActivity.this, "Default Timer is set", Toast.LENGTH_LONG).show();
                    }else{
                        limitTime = Double.parseDouble(etTimer.getText().toString());

                        if (limitTime > 30){
                            etTimer.setText("30");
                            Toast.makeText(MainActivity.this, "Maximum of 30 Minutes only", Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

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

                    lvl = lvlRG.getCheckedRadioButtonId();
                    RBlvl = findViewById(lvl);
                    rbLvlText = RBlvl.getText().toString();

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

                    if (mode.equals("Normal")){
                        if ((etTimer.getText().toString()).equals("")){
                            timer = "0";
                        }else{
                            timer = etTimer.getText().toString();
                        }
                    }

                    Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                    intent.putExtra("mode", mode);
                    intent.putExtra("level", rbLvlText);
                    intent.putExtra("Calcu", rbCalText);
                    intent.putExtra("max", selRange);
                    intent.putExtra("timer", timer);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences getLatestSP = getSharedPreferences("PrevScore", MODE_PRIVATE);

        prevCategory = getLatestSP.getString("category", "");
        prevScore = getLatestSP.getInt("score", 0);
        prevTime = getLatestSP.getString("time", "");
        prevCompletion = getLatestSP.getString("completion", "");

        if (prevTime.equals("") && prevCategory.equals("") && prevScore == 0){
            tvPrev.setText("Previous Score:\nNo Data Detected Recently");
        }else{
            tvPrev.setText("Previous Score ( " + selMode + " ) :\nCategory: " + prevCategory + "\nScore: " + prevScore + "/10 ( " + prevCompletion + " )\nTime Left: " + prevTime);
        }

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbTextLvl = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        if(rbTextLvl.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(rbTextLvl.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(rbTextLvl.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(rbTextLvl.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(rbTextLvl.equals("Beginner")){

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

        }else if(rbTextLvl.equals("Intermediate")){

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

        }else if(rbTextLvl.equals("Advanced")){

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

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbTextLvl = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if(rbTextLvl.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbTextLvl.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbTextLvl.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbTextLvl.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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

        prevCategory = getLatestSP.getString("category", "");
        prevScore = getLatestSP.getInt("score", 0);
        prevTime = getLatestSP.getString("time", "");
        prevCompletion = getLatestSP.getString("completion", "");

        if (prevTime.equals("") && prevCategory.equals("") && prevScore == 0){
            tvPrev.setText("Previous Score:\nNo Data Detected Recently");
        }else{
            tvPrev.setText("Previous Score ( " + selMode + " ) :\nCategory: " + prevCategory + "\nScore: " + prevScore + "/10 ( " + prevCompletion + " )\nTime Left: " + prevTime);
        }

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbTextLvl = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbTextCal = RBcal.getText().toString();

        if(rbTextLvl.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(rbTextLvl.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(rbTextLvl.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(rbTextLvl.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
        }else if(rbTextLvl.equals("Beginner")){

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

        }else if(rbTextLvl.equals("Intermediate")){

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

        }else if(rbTextLvl.equals("Advanced")){

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

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbTextLvl = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbTextCal = RBcal.getText().toString();

                if(rbTextLvl.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbTextLvl.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbTextLvl.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbTextLvl.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbTextLvl.equals("Random") && rbTextCal.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_LONG).show();
                }else if(rbTextLvl.equals("Beginner")){

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

                }else if(rbTextLvl.equals("Intermediate")){

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

                }else if(rbTextLvl.equals("Advanced")){

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
