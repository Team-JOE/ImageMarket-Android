package com.aosproject.imagemarket.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aosproject.imagemarket.R;

public class DealActivityHK extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_hk);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


    }
}