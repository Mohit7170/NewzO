package com.app.newzo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;


public class SplashScreen extends AppCompatActivity {

    private static final String TAG = "Splash Activity";
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private boolean shouldShowAdds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(1)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);


        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
        getValuefromFirebase();

        Thread thread = new Thread() {

            @Override
            public void run() {

                try {
                    sleep(1500);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("shouldShowAdds", shouldShowAdds));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();
    }

    private void getValuefromFirebase() {


        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d(TAG, "Config params updated: " + updated);
                            Toast.makeText(SplashScreen.this, "Fetch and activate succeeded",
                                    Toast.LENGTH_SHORT).show();

                            shouldShowAdds = mFirebaseRemoteConfig.getBoolean("set_adds");

                        } else {
                            Toast.makeText(SplashScreen.this, "Fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}