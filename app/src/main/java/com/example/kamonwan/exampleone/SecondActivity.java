package com.example.kamonwan.exampleone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    int sum = 0;
    TextView tvResult;
    Bundle bundle;
    Button btnOk;
    EditText editTextSecond;
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
        btnOk = (Button) findViewById(R.id.btnOk);
        editTextSecond = (EditText) findViewById(R.id.editTextSecond);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", editTextSecond.getText().toString());
                setResult(RESULT_OK,returnIntent);
                finish();
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
    }
}
