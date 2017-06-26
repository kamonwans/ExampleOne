package com.example.kamonwan.exampleone;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvHello, tvResults;
    EditText editTextHello, editText1, editText2;
    Button btnCoppy, btnCalculate;
    CustomViewGroup viewGroup1,viewGroup2;

    private RadioGroup rgOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Toast.makeText(MainActivity.this, "Width = " + width + "Height = " + height, Toast.LENGTH_SHORT).show();



    }

    private void initstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setText("");

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in EditText to TextView
                    tvHello.setText(editTextHello.getText());
                    return true;
                }
                return false;

            }
        });
        btnCoppy = (Button) findViewById(R.id.btnCoppy);
        btnCoppy.setOnClickListener(this);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        tvResults = (TextView) findViewById(R.id.tvResults);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        rgOperator = (RadioGroup) findViewById(R.id.rgOperator);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val1 = 0;
                int val2 = 0;
                int sum = 0;
                try {
                    val1 = Integer.parseInt(editText1.getText().toString());
                } catch (NumberFormatException e) {
                }
                try {
                    val2 = Integer.parseInt(editText2.getText().toString());
                } catch (NumberFormatException e) {
                }

                // Check Operator
                switch (rgOperator.getCheckedRadioButtonId()) {
                    case R.id.rdPlus:
                        sum = val1 + val2;
                        break;
                    case R.id.rdMinus:
                        sum = val1 - val2;
                        break;
                    case R.id.rdMultiply:
                        sum = val1 * val2;
                        break;
                    case R.id.rdDivide:
                        sum = val1 / val2;
                        break;
                }
                // Show result
                tvResults.setText(sum + "");
                Log.d("Calculation", "Result = " + sum);
                Toast.makeText(MainActivity.this, "Result" + sum, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("Result", sum);
                startActivity(intent);
            }
        });
        viewGroup1 = (CustomViewGroup) findViewById(R.id.ViewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.ViewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");
    }

    @Override
    public void onClick(View v) {
        if (v == btnCoppy) {
            tvHello.setText(editTextHello.getText());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            //Do what you want

            //Handled

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}







