package com.example.mark5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class MainEmptyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }


        Timber.d("Checkpoint3 MainEmptyActivity Created");
        Timber.d("Checkpoint3 Timber Initialized");

        Intent activityIntent;

        // go straight to main if a token is stored
//        if (Util.getToken() != null) {
//            activityIntent = new Intent(this, MainActivity.class);
//        } else {
//        }
        activityIntent = new Intent(this, SignInActivity.class);

        startActivity(activityIntent);
        finish();
    }


    /*Checkpoint 3*/
    @Override
    public void onPause() {
        super.onPause();
        Timber.d("Checkpoint3 MainEmptyActivity onPause");
    }
    @Override
    public void onResume() {
        super.onResume();
        Timber.d("Checkpoint3 MainEmptyActivity onResume");
    }
    @Override
    public void onStop() {
        super.onStop();
        Timber.d("Checkpoint3 MainEmptyActivity onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("Checkpoint3 MainEmptyActivity onDestroy");
    }
}
