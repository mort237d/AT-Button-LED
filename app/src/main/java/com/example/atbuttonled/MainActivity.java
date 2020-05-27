package com.example.atbuttonled;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.util.zip.ZipInputStream;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Gpio redLED;
    private Gpio greenLED;
    private Gpio blueLED;
    private Gpio redBTN;
    private Gpio greenBTN;
    private Gpio blueBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeripheralManager manager = PeripheralManager.getInstance();

        try {
            redLED = manager.openGpio("BCM19");
            redLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
            greenLED = manager.openGpio("BCM16");
            greenLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
            blueLED = manager.openGpio("BCM20");
            blueLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);

            redBTN = manager.openGpio("BCM4");
            redBTN.setDirection(Gpio.DIRECTION_IN);
            greenBTN = manager.openGpio("BCM17");
            greenBTN.setDirection(Gpio.DIRECTION_IN);
            blueBTN = manager.openGpio("BCM27");
            blueBTN.setDirection(Gpio.DIRECTION_IN);

            while (true){
                redLED.setValue(!redBTN.getValue());
                greenLED.setValue(!greenBTN.getValue());
                blueLED.setValue(!blueBTN.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (redLED != null) {
            try { redLED.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
        if (greenLED != null) {
            try { greenLED.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
        if (blueLED != null) {
            try { blueLED.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
        if (redBTN != null) {
            try { redBTN.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
        if (greenBTN != null) {
            try { greenBTN.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
        if (blueBTN != null) {
            try { blueBTN.close(); }
            catch (IOException e) { Log.e(TAG, "Error on PeripheralIO API", e); }
        }
    }
}
