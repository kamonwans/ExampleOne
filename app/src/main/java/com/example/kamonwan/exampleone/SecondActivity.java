package com.example.kamonwan.exampleone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvResult;
    Bundle bundle;
    int x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        sum = intent.getIntExtra("Result", 0);

        bundle = intent.getBundleExtra("cBundle");
        x = bundle.getInt("x");
        y = bundle.getInt("y");
        z = bundle.getInt("z");

        CoordinateSerializable c2 = (CoordinateSerializable) intent.getSerializableExtra("cSerializable");
        CoordinateParcelable c3 = intent.getParcelableExtra("cParcelable");

        initInstances();
    }

    private void initInstances() {

        tvResult = (TextView) findViewById(R.id.tvResults);
        tvResult.setText("Result = " + sum);
    }
}
