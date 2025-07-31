package com.example.luckynumberapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class secondActivity extends AppCompatActivity {

    TextView textView ;
    Button shareResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        textView = findViewById(R.id.resultText);
        shareResult = findViewById(R.id.shareResult);

        String number = String.valueOf(generateRandomNumber());

        textView.setText(number);

        shareResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareResultData(name,number);

            }
        });


    }




    public int generateRandomNumber()
    {
        int upperLimit = 1000;

        Random random = new Random();
        return random.nextInt(upperLimit);
    }



    public void shareResultData(String name,String luckyNumber)
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_SUBJECT,name+"GOt Lucky Today");
        i.putExtra(Intent.EXTRA_TEXT,"His Lucky Number is "+luckyNumber);

        startActivity(Intent.createChooser(i,"Select the App to Share"));

    }


}