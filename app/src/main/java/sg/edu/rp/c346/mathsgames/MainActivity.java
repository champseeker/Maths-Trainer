package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvSB, tvTimer;
    EditText etTimer, etRandLimit;
    RadioGroup lvlRG, calRG;
    RadioButton RBlvl, RBcal;
    SeekBar rSB;
    Button conBtn;

    String rbtext, rbtextDif;
    Integer lvl, cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        tvTimer = findViewById(R.id.tvTime);
        etTimer = findViewById(R.id.etTimer);
        conBtn = findViewById(R.id.btnCon);
        tvSB = findViewById(R.id.sbText);
        lvlRG = findViewById(R.id.RBlvl);
        calRG = findViewById(R.id.RBcal);
        rSB = findViewById(R.id.sbRange);
        etRandLimit = findViewById(R.id.etRandLimit);

        rSB.refreshDrawableState();

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbtext = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbtextDif = RBcal.getText().toString();

        if(rbtext.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(rbtext.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(rbtext.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(rbtext.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (rbtext.equals("Random") && rbtextDif.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
        }

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbtext = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if(rbtext.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbtext.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbtext.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbtext.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
                }

            }
        });

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
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
                Toast.makeText(MainActivity.this, "Range is set as 0 - " + sbValue, Toast.LENGTH_SHORT).show();
            }
        });

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String timer, rbLvlText, rbCalText, selRange;

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbLvlText = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbCalText = RBcal.getText().toString();

                if (etRandLimit.getVisibility() == View.VISIBLE){
                    selRange = etRandLimit.getText().toString();
                }else{
                    selRange = tvSB.getText().toString();
                }

                if ((etTimer.getText().toString()).equals("")){
                    timer = "0";
                }else{
                    timer = etTimer.getText().toString();
                }

                Intent intent = new Intent(getBaseContext(), SecondActivity.class);
                intent.putExtra("level", rbLvlText);
                intent.putExtra("Calcu", rbCalText);
                intent.putExtra("max", selRange);
                intent.putExtra("timer", timer);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbtext = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbtextDif = RBcal.getText().toString();

        if(rbtext.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(rbtext.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(rbtext.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(rbtext.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (rbtext.equals("Random") && rbtextDif.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
        }

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
                }

            }
        });

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbtext = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if(rbtext.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbtext.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbtext.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbtext.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        lvl = lvlRG.getCheckedRadioButtonId();
        RBlvl = findViewById(lvl);
        rbtext = RBlvl.getText().toString();

        cal = calRG.getCheckedRadioButtonId();
        RBcal = findViewById(cal);
        rbtextDif = RBcal.getText().toString();

        if(rbtext.equals("Beginner")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(10);
        }else if(rbtext.equals("Intermediate")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(15);
        }else if(rbtext.equals("Advanced")){
            rSB.setVisibility(View.VISIBLE);
            tvSB.setVisibility(View.VISIBLE);
            etRandLimit.setVisibility(View.GONE);
            rSB.setMax(20);
        }else if(rbtext.equals("Random")){
            rSB.setVisibility(View.GONE);
            tvSB.setVisibility(View.GONE);
            etRandLimit.setVisibility(View.VISIBLE);
        }

        if (rbtext.equals("Random") && rbtextDif.equals("Random")){
            Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
        }

        calRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
                }

            }
        });

        lvlRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                lvl = lvlRG.getCheckedRadioButtonId();
                RBlvl = findViewById(lvl);
                rbtext = RBlvl.getText().toString();

                cal = calRG.getCheckedRadioButtonId();
                RBcal = findViewById(cal);
                rbtextDif = RBcal.getText().toString();

                if(rbtext.equals("Beginner")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(10);
                }else if(rbtext.equals("Intermediate")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(15);
                }else if(rbtext.equals("Advanced")){
                    rSB.setVisibility(View.VISIBLE);
                    tvSB.setVisibility(View.VISIBLE);
                    etRandLimit.setVisibility(View.GONE);
                    rSB.setMax(20);
                }else if(rbtext.equals("Random")){
                    rSB.setVisibility(View.GONE);
                    tvSB.setVisibility(View.GONE);
                    etRandLimit.setVisibility(View.VISIBLE);
                }

                if (rbtext.equals("Random") && rbtextDif.equals("Random")){
                    Toast.makeText(MainActivity.this, "Random is selected for both Difficulty and Calculation Method. \nYou are in for a big surprise. \nBetter start praying you have good luck today." , Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
