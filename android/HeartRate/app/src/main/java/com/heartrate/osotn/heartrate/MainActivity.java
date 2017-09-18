package com.heartrate.osotn.heartrate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        Log.i(TAG, "onCreate()");

        Log.i(TAG, "onCreate() - create a thread");
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.i(TAG, "onRun() - 1");
                Log.i(TAG, "onRun() - 2");
                Log.i(TAG, "onRun() - 3");
                Log.i(TAG, "onRun() - 4");

            }
            private static final String TAG = "MyThread";
        }).start();

        Intent intent = new Intent (Intent.ACTION_CALL, Uri.parse("tel:*111#"));
        startActivity(intent);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    private static final String TAG = "MyActivity";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
