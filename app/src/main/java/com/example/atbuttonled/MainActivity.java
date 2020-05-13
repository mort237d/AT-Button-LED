package com.example.atbuttonled;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the PeripheralManager
 * For example, the snippet below will open a GPIO pin and set it to HIGH:
 * <p>
 * PeripheralManager manager = PeripheralManager.getInstance();
 * try {
 * Gpio gpio = manager.openGpio("BCM6");
 *
 * gpio.setValue(true);
 * } catch (IOException e) {
 * Log.e(TAG, "Unable to access GPIO");
 * }
 * <p>
 * You can find additional examples on GitHub: https://github.com/androidthings
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeripheralManager manager = PeripheralManager.getInstance();

        try {
            Gpio redLED = manager.openGpio("BCM19");
            redLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
            Gpio greenLED = manager.openGpio("BCM16");
            greenLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);
            Gpio blueLED = manager.openGpio("BCM20");
            blueLED.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);

            Gpio redBTN = manager.openGpio("BCM4");
            redBTN.setDirection(Gpio.DIRECTION_IN);
            Gpio greenBTN = manager.openGpio("BCM17");
            greenBTN.setDirection(Gpio.DIRECTION_IN);
            Gpio blueBTN = manager.openGpio("BCM27");
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
}
