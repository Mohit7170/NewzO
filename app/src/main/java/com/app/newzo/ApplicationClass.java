package com.app.newzo;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

public class ApplicationClass extends Application {

    private static final String ONESIGNAL_APP_ID = "478e0382-d7aa-460e-8d1c-fda50ebc9e6e";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        // Android SDK 4.x.x
        OneSignal.setNotificationOpenedHandler(
                new OneSignal.OSNotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenedResult result) {
                        // Capture Launch URL (App URL) here
                        String launchUrl = result.getNotification().getLaunchURL();
                        Log.i("OneSignalExample", "launchUrl set with value: " + launchUrl);
                        if (launchUrl != null) {
                            // The following can be used to open an Activity of your choice.
                            // Replace - getApplicationContext() - with any Android Context.
                            // Replace - YOURACTIVITY.class with your activity to deep link
                            Intent intent = new Intent(getApplicationContext(), web.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("url", launchUrl);
                            Log.i("OneSignalExample", "openURL = " + launchUrl);
                            startActivity(intent);
                        }
                    }
                });

    }

}
