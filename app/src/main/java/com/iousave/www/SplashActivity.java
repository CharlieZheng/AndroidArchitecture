package com.iousave.www;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                it.putExtra(MainActivity.class.getName(), MainActivity.Type._0);
                startActivity(it);
            }
        });
        ((Button) findViewById(R.id.bookSearch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                it.putExtra(MainActivity.class.getName(), MainActivity.Type._1);
                startActivity(it);
            }
        });
        ((Button) findViewById(R.id.verificationCode)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SplashActivity.this, MainActivity.class);
                it.putExtra(MainActivity.class.getName(), MainActivity.Type._2);
                startActivity(it);
            }
        });
    }
}
