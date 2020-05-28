package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    TextView tvScore, tvResult, tvQues, tvNum, tvTime;
    Button btnSub, btnBack;
    EditText etAns;

    String lvl, method, maxRange, equa, timer, product, category, sText, mode;
    Integer randNum, randMul, time, randPro, randRand;
    Double answer, ans, sTime;

    Question questionObj;

    int score = 0;
    int questionNumber = 0;

    ArrayList<Question> questionList = new ArrayList<Question>();
    ArrayList<String> products = new ArrayList<String>();
    ArrayList<Integer> randTimer = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);

        SharedPreferences setLatestSP = getSharedPreferences("PrevScore", MODE_PRIVATE);
        final SharedPreferences.Editor myEdit  = setLatestSP.edit();

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
        mode = intentReceived.getStringExtra("mode");

        sTime = Double.parseDouble(timer);

        randTimer.add(1200000);
        randTimer.add(900000);
        randTimer.add(600000);
        randTimer.add(300000);
        randRand = new Random().nextInt(4 - 0) + 0;

        if (method.equals("Random") && lvl.equals("Random")) {

            time = randTimer.get(randRand);

        }else if(sTime == 0){

            if(lvl.equals("Beginner")){

                if (method.equals("Addition")) {

                    time = 1200000;

                } else if (method.equals("Subtraction")) {

                    time = 1200000;

                } else if (method.equals("Multiplication")) {

                    time = 900000;

                } else if (method.equals("Division")) {

                    time = 900000;

                } else if (method.equals("Random")) {

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

                } else if (method.equals("Random")) {

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

                } else if (method.equals("Random")) {

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


        category = lvl + " - " + method;
        Log.i("TAG", "onCreate: lmao " + category);

        final CountDownTimer timer = new CountDownTimer(time, 1) {

            public void onTick(long millisUntilFinished) {

                String text = String.format(Locale.getDefault(), "Time Remaining: %02d hour %02d min and %02d sec",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                tvTime.setText(text);

                sText = String.format(Locale.getDefault(), "%02d hour, %02d min and %02d sec",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
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
                myEdit.putString("mode", mode);
                myEdit.putInt("score", score);
                myEdit.putString("category", category);
                myEdit.putString("time", sText);
                myEdit.putString("completion", "Not Completed");
                myEdit.commit();
            }

        }.start();

        int min = 0;
        int max = Integer.parseInt(maxRange) + 1;

        products.add("+");
        products.add("-");
        products.add("x");
        products.add("÷");

        for (int i = 1; i <= 10; i++) {

            randMul = new Random().nextInt(max - min) + min;
            randNum = new Random().nextInt(max - min) + min;
            randPro = new Random().nextInt(4 - 0) + 0;

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

            } else if (method.equals("Random")) {
                product = products.get(randPro);

                if(product.equals("+")){
                    equa = randNum + " + " + randMul + " = ";
                    answer = (double) randNum + randMul;
                }else if(product.equals("-")){
                    equa = randNum + " - " + randMul + " = ";
                    answer = (double) randNum - randMul;
                }else if(product.equals("x")){
                    equa = randNum + " x " + randMul + " = ";
                    answer = (double) randNum * randMul;
                }else if(product.equals("÷")){
                    randMul = new Random().nextInt(max - 1) + 1;
                    equa = randNum + " ÷ " + randMul + " = ";
                    answer = (double) randNum / randMul;
                    answer = Double.parseDouble(String.format("%.2f", answer));
                }

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
                    myEdit.putString("mode", mode);
                    myEdit.putInt("score", score);
                    myEdit.putString("category", category);
                    myEdit.putString("time", sText);
                    myEdit.putString("completion", "Completed");
                    myEdit.commit();
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


