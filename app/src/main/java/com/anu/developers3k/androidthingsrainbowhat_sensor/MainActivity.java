package com.anu.developers3k.androidthingsrainbowhat_sensor;

/**
 * Created by anurajr on 28/12/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.things.contrib.driver.bmx280.Bmx280;
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.pwmservo.Servo;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

/**
 * Use the sensors in RainbowHAT
 */
public class MainActivity extends Activity {

    private Handler handler;
    private Bmx280 sensor;
    private AlphanumericDisplay display;

    private TextView mResultText;


    Servo servo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main);

        mResultText = findViewById(R.id.textView);

        try {

            servo = new Servo("PWM0");
            servo.setPulseDurationRange(1, 2); // according to your servo's specifications
            servo.setAngleRange(0, 180);       // according to your servo's specifications
            servo.setEnabled(true);
            servo.setAngle(90);



            sensor = RainbowHat.openSensor();
            display = RainbowHat.openDisplay();
            handler = new Handler(getMainLooper());

            sensor.setMode(Bmx280.MODE_NORMAL);
            sensor.setTemperatureOversampling(Bmx280.OVERSAMPLING_1X);
            sensor.setPressureOversampling(Bmx280.OVERSAMPLING_1X);
            display.setEnabled(true);
            runAndSchedule();

            servo.setPulseDurationRange(1, 2);
            servo.setAngleRange(-90, 90);
            servo.setEnabled(true);

            servo.setAngle(servo.getMinimumAngle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAndSchedule() {
        try {
            //displaying the temperature or pressure value in display
            //display.display(sensor.readPressure());
            display.display(sensor.readTemperature());
            mResultText.setText(String.valueOf(sensor.readTemperature()+ "°C"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.postDelayed(this::runAndSchedule,300);

    }
}