package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.weatherapp.databinding.CustomAlertDialogBinding;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onBackPressed() {
        showAlertDialog();
    }

    private void showAlertDialog() {
        CustomAlertDialogBinding binding = CustomAlertDialogBinding.inflate(getLayoutInflater());
        alertDialog = new androidx.appcompat.app.AlertDialog.Builder(this).setView(binding.getRoot()).create();
        alertDialog.show();
        binding.btnYes.setOnClickListener(view1 -> finish());
        binding.btnNo.setOnClickListener(view12 -> alertDialog.cancel());
    }
}