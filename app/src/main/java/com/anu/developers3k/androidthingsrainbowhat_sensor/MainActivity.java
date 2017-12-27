package com.anu.developers3k.androidthingsrainbowhat_sensor;

/**
 * Created by anurajr on 28/12/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.things.contrib.driver.bmx280.Bmx280;
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

/**
 * Use the sesnors in RainbowHAT
 */
public class MainActivity extends Activity {

    Handler handler;
    Gpio led;
    boolean value;
    Bmx280 sensor;
    AlphanumericDisplay display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            sensor = RainbowHat.openSensor();
            display = RainbowHat.openDisplay();


            //choose the green led in the board.
            led = RainbowHat.openLedRed();
            led.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
            handler = new Handler(getMainLooper());
            value =true;

            //loop for blinking the led
            runAndSchedule();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAndSchedule() {
        try {
            led.setValue(value);
            value =!value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.postDelayed(this::runAndSchedule,300);

    }
}