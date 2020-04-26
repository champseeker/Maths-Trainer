package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.DoubleBuffer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    TextView tvScore, tvResult, tvQues, tvNum, tvTime;
    Button btnSub, btnBack;
    EditText etAns;

    String lvl, method, maxRange, equa, timer;
    Integer randNum, randMul, time;
    Double answer, ans, sTime;

    Question questionObj;

    int score = 0;
    int questionNumber = 0;

    ArrayList<Question> questionList = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);

        tvTime = findViewById(R.id.tvTime);
        tvScore = findViewById(R.id.tvScore);
        tvResult = findViewById(R.id.tvResult);
        tvQues = findViewById(R.id.tvQues);
        tvNum = findViewById(R.id.tvQuesNum);
        btnSub = findViewById(R.id.btnCheck);
        btnBack = findViewById(R.id.btnBack);
        etAns = findViewById(R.id.etAns);
        btnBack.setVisibility(View.GONE);

        Intent intentReceived = getIntent();
        lvl = intentReceived.getStringExtra("level");
        method = intentReceived.getStringExtra("Calcu");
        maxRange = intentReceived.getStringExtra("max");
        timer = intentReceived.getStringExtra("timer");

        sTime = Double.parseDouble(timer);


        if(sTime == 0){

            if(lvl.equals("Beginner")){

                if (method.equals("Addition")) {

                    time = 1200000;

                } else if (method.equals("Subtraction")) {

                    time = 1200000;

                } else if (method.equals("Multiplication")) {

                    time = 900000;

                } else if (method.equals("Division")) {

                    time = 900000;

                }

            }else if(lvl.equals("Intermediate")){

                if (method.equals("Addition")) {

                    time = 900000;

                } else if (method.equals("Subtraction")) {

                    time = 900000;

                } else if (method.equals("Multiplication")) {

                    time = 600000;

                } else if (method.equals("Division")) {

                    time = 600000;

                }

            }else if(lvl.equals("Advanced")){

                if (method.equals("Addition")) {

                    time = 600000;

                } else if (method.equals("Subtraction")) {

                    time = 600000;

                } else if (method.equals("Multiplication")) {

                    time = 300000;

                } else if (method.equals("Division")) {

                    time = 300000;

                }

            }else{

                if (method.equals("Addition")) {

                    time = 600000;

                } else if (method.equals("Subtraction")) {

                    time = 600000;

                } else if (method.equals("Multiplication")) {

                    time = 300000;

                } else if (method.equals("Division")) {

                    time = 300000;

                }

            }

        }else{

            sTime = sTime* Double.parseDouble("60000");
            time = sTime.intValue();

        }

        final CountDownTimer timer = new CountDownTimer(time, 1) {

            public void onTick(long millisUntilFinished) {

                String text = String.format(Locale.getDefault(), "Time Remaining: %02d hour %02d min and %02d sec",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), // The change is in this line
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                tvTime.setText(text);
            }

            public void onFinish() {
                btnBack.setVisibility(View.VISIBLE);
                btnSub.setVisibility(View.GONE);
                etAns.setVisibility(View.GONE);
                tvNum.setVisibility(View.GONE);
                tvScore.setVisibility(View.GONE);
                tvQues.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
                tvQues.setTypeface(null, Typeface.BOLD);
                tvQues.setText("Congratulation, You have completed " + (questionNumber - 1) + " Question\nYou can go Back to set a different Level Of Difficulty\nYour total Score is " + score + "/10");
                tvTime.setText("Times Up");
            }

        }.start();

        int min = 0;
        int max = Integer.parseInt(maxRange) + 1;

        for (int i = 1; i <= 10; i++) {

            randMul = new Random().nextInt(max - min) + min;
            randNum = new Random().nextInt(max - min) + min;

            if (method.equals("Addition")) {
                equa = randNum + " + " + randMul + " = ";
                answer = (double) randNum + randMul;

            } else if (method.equals("Subtraction")) {
                equa = randNum + " - " + randMul + " = ";
                answer = (double) randNum - randMul;

            } else if (method.equals("Multiplication")) {
                equa = randNum + " x " + randMul + " = ";
                answer = (double) randNum * randMul;

            } else if (method.equals("Division")) {
                randMul = new Random().nextInt(max - 1) + 1;
                equa = randNum + " ÷ " + randMul + " = ";
                answer = (double) randNum / randMul;
                answer = Double.parseDouble(String.format("%.2f", answer));

            }

            Question ques = new Question(i, equa, answer);
            questionList.add(ques);

        }

        questionObj = questionList.get(questionNumber);

        setView();

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (etAns.getText().toString().equals("")) {
                Toast.makeText(SecondActivity.this, "Enter your Answer", Toast.LENGTH_SHORT).show();
            } else {

                ans = Double.parseDouble(etAns.getText().toString());

                if ((ans == Math.floor(ans)) && !Double.isInfinite(ans)) {

                    Integer ans = Integer.parseInt(etAns.getText().toString());

                    Integer cAnswer = Integer.valueOf(questionObj.getAnswer().intValue());

                    if (cAnswer.equals(ans)) {
                        score++;
                        tvResult.append("\n");
                        String eq = getColoredSpanned("Q" + questionNumber + ")  " + questionObj.getQuestion() + ans, "#006400");
                        tvResult.append(Html.fromHtml(eq));
                        etAns.setText("");
                        tvScore.setText("Current Score: " + score + "/10");
                    } else {
                        tvResult.append("\n");
                        String eq = getColoredSpanned("Q" + questionNumber + ")  " + questionObj.getQuestion() + ans, "#FF0000");
                        tvResult.append(Html.fromHtml(eq));
                        etAns.setText("");
                        tvScore.setText("Current Score: " + score + "/10");
                    }

                } else {

                    if (questionObj.getAnswer().equals(ans)) {
                        score++;
                        tvResult.append("\n");
                        String eq = getColoredSpanned("Q" + questionNumber + ")  " + questionObj.getQuestion() + ans, "#006400");
                        tvResult.append(Html.fromHtml(eq));
                        etAns.setText("");
                        tvScore.setText("Current Score: " + score + "/10");
                    } else {
                        tvResult.append("\n");
                        String eq = getColoredSpanned("Q" + questionNumber + ")  " + questionObj.getQuestion() + ans, "#FF0000");
                        tvResult.append(Html.fromHtml(eq));
                        etAns.setText("");
                        tvScore.setText("Current Score: " + score + "/10");
                    }

                }

                if (questionNumber < questionList.size()) {
                    questionObj = questionList.get(questionNumber);
                    setView();
                } else if (questionNumber == 10) {
                    timer.cancel();
                    btnBack.setVisibility(View.VISIBLE);
                    btnSub.setVisibility(View.GONE);
                    etAns.setVisibility(View.GONE);
                    tvNum.setVisibility(View.GONE);
                    tvScore.setVisibility(View.GONE);
                    tvQues.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
                    tvQues.setTypeface(null, Typeface.BOLD);
                    tvQues.setText("Congratulation, You have completed all 10 Question\nYou can go Back to set a different Level Of Difficulty\nYour total Score is " + score + "/10");
                }

            }

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "\n" + "</font>";
        return input;
    }

    private void setView() {
        tvQues.setText(questionObj.getQuestion());
        questionNumber++;
        tvNum.setText("Q" + questionNumber);
    }

}


