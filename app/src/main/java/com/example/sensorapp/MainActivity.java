package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity; //buat sensor yg ada hrs dibuat 2 variable. 1 sensor 1
    private Sensor mSensorTemperature;
    private Sensor mSensorPressure;
    private Sensor mSensorHumidity;


    private TextView mTextSensorLight;
    private TextView mTextSensorProximity; //txtview dai xml
    private TextView mTextSensorTemperature;
    private TextView mTextSensorPressure;
    private TextView mTextSensorHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

    //    List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
    //    StringBuilder sensorText = new StringBuilder();
    //    for (Sensor currentSensor : sensorList) {
    //        sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
    //    }
    //    TextView sensorTextView = findViewById(R.id.sensor_list);
    //    sensorTextView.setText(sensorText);

        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        mTextSensorTemperature = findViewById(R.id.label_temperature);
        mTextSensorPressure = findViewById(R.id.label_pressure);
        mTextSensorHumidity = findViewById(R.id.label_humidity);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String sensor_error = "No Sensor";

        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null){
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorTemperature == null){
            mTextSensorTemperature.setText(sensor_error);
        }
        if (mSensorPressure == null){
            mTextSensorPressure.setText(sensor_error);
        }
        if (mSensorHumidity == null){
            mTextSensorHumidity.setText(sensor_error);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorLight == null) return;
        mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);

        if (mSensorProximity == null) return;
        mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);

        if (mSensorTemperature == null) return;
        mSensorManager.registerListener(this, mSensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);

        if (mSensorPressure == null) return;
        mSensorManager.registerListener(this, mSensorPressure, SensorManager.SENSOR_DELAY_NORMAL);

        if (mSensorHumidity == null) return;
        mSensorManager.registerListener(this, mSensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentValue = event.values[0];
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(getString(R.string.label_light, currentValue));
                changeBackgroundColor(currentValue);
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(getString(R.string.label_proximity, currentValue));
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorTemperature.setText(getString(R.string.label_temperature, currentValue));
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(getString(R.string.label_pressure, currentValue));
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(getString(R.string.label_humidity, currentValue));
                break;
            default:
                //nothing
        }
    }

    private void changeBackgroundColor(float currentValue) {
        LinearLayout layout = findViewById(R.id.constraint_layout);
        if(currentValue <= 40000 && currentValue >= 20000) layout.setBackgroundColor(Color.RED);
        else if (currentValue < 20000 && currentValue >= 10) layout.setBackgroundColor(Color.BLUE);
        else if (currentValue < 10) layout.setBackgroundColor(Color.WHITE);
        }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}