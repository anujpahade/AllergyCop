package com.example.mark5;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.d("MainActivity Created");
    }
    /*
    * Checkpoint 3
    */
    @Override
    public void onPause() {
        super.onPause();
        Timber.d("Checkpoint MainActivity onPause");
    }
    @Override
    public void onResume() {
        super.onResume();
        Timber.d("Checkpoint MainActivity onResume");
    }
    @Override
    public void onStop() {
        super.onStop();
        Timber.d("Checkpoint MainActivity onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Checkpoint MainActivity onDestroy");
    }
}

