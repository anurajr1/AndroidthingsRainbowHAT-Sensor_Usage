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

import java.io.IOException;

/**
 * Use the sensors in RainbowHAT
 */
public class MainActivity extends Activity {

    Handler handler;
    Bmx280 sensor;
    AlphanumericDisplay display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            sensor = RainbowHat.openSensor();
            display = RainbowHat.openDisplay();

            sensor.setMode(Bmx280.MODE_NORMAL);
            sensor.setTemperatureOversampling(Bmx280.OVERSAMPLING_1X);
           // sensor.setPressureOversampling(Bmx280.OVERSAMPLING_1X);
            display.setEnabled(true);
            runAndSchedule();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAndSchedule() {
        try {
            //displaying the temperature or pressure value in display
            //display.display(sensor.readPressure());
            display.display(sensor.readTemperature());
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.postDelayed(this::runAndSchedule,300);

    }
}