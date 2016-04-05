package com.iappsasia.iapps.Fragments;


import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iappsasia.iapps.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SensorEventListener {

    @Bind(R.id.cvStepCounter)
    CardView cvStepCounterHolder;
    @Bind(R.id.tvSteps)
    TextView tvSteps;
    private View v;

    private SensorManager mSensorManager;
    private Sensor mStepCountSensor, mStepDetectorSensor;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mStepCountSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


    }

    @Override
    public void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mStepCountSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCountSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mSensor = sensorEvent.sensor;
        float[] values = sensorEvent.values;

        int value = -1;

        if(values.length > 0) {
            value = (int) values[0];
        }

        if(mSensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            tvSteps.setText(value);
        } else if(mSensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            tvSteps.setText(value);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
