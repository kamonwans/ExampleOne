package com.example.kamonwan.exampleone;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.PersistableBundle;
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

    int x,y,z; //Save/Restore in Activity instance state
    TextView tvHello, tvResults;
    EditText editTextHello, editText1, editText2;
    Button btnCoppy, btnCalculate;
    CustomViewGroup viewGroup1, viewGroup2;

    private RadioGroup rgOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            //Fix
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
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

                // Playground
                Coordinate c1 = new Coordinate();
                c1.x = 5;
                c1.y = 10;
                c1.z = 20;
                Bundle bundle = new Bundle();
                bundle.putInt("x", c1.x);
                bundle.putInt("y", c1.y);
                bundle.putInt("z", c1.z);
                intent.putExtra("cBundle", bundle);

                //Serializable Lab
                CoordinateSerializable c2 = new CoordinateSerializable();
                c2.x = 5;
                c2.y = 10;
                c2.z = 20;
                intent.putExtra("cSerializable", c2);

                CoordinateParcelable c3 = new CoordinateParcelable();
                c3.x = 5;
                c3.y = 10;
                c3.z = 20;
                intent.putExtra("cParcelable", c3);
                startActivityForResult(intent,12345);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        });
        viewGroup1 = (CustomViewGroup) findViewById(R.id.ViewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.ViewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check if it a result from SecondActivity
        if (requestCode == 12345) {
            if (requestCode == RESULT_OK) {
                //Get data from data's extra
                String result = data.getStringExtra("result");
                Toast.makeText(MainActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        }
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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save here
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //Restore here
    }
}







