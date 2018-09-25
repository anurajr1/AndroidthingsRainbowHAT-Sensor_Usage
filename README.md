# AndroidthingsRainbowHAT-Sensor_Usage
Demo of using the Rainbow HAT sensors

Using Pico pro maker kit - https://developer.android.com/things/hardware/imx7d-kit.html

NXP i.MX7D board - https://developer.android.com/things/hardware/imx7d.html

Display shows the temperature reading from the rainbowHAT. You can change the code to read the pressure information as well.


Add the dependencies in gradle
```
dependencies {
    compileOnly 'com.google.android.things:androidthings:+'
    compile 'com.google.android.things.contrib:driver-rainbowhat:0.8'
}
```

## Demo

![videotogif_2017 12 27_23 21 32](https://user-images.githubusercontent.com/18279724/34388973-dfba8252-eb5c-11e7-974f-054cc27b38f0.gif)























