package sg.edu.rp.c346.mathsgames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class ModeActivity extends AppCompatActivity {

    Button btnNorm, btnCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mode);
        getSupportActionBar().hide();

        btnNorm = findViewById(R.id.btnNormal);
        btnCom = findViewById(R.id.btnComp);

        btnNorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("mode", "Normal");
                startActivity(intent);

            }
        });

        btnCom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                intent.putExtra("mode", "Competitive");
                startActivity(intent);

            }
        });

    }
}
