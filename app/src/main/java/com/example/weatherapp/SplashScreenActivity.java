package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.weatherapp.databinding.ActivitySplashScreenBinding;

public class SplashScreenActivity extends AppCompatActivity {
    private ActivitySplashScreenBinding binding;
    private int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //progressBar.setScaleY(3f);

        Thread thread = new Thread(() -> {
            doWork();
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        });
        thread.start();
    }

    private void doWork() {

        for (progress = 20; progress <= 100; progress += 20) {
            try {
                Thread.sleep(300);
                binding.progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}