package com.turaapp.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.zero);
        button1 = findViewById(R.id.one);
        button2 = findViewById(R.id.two);
        button3 = findViewById(R.id.three);
        button4 = findViewById(R.id.four);
        button5 = findViewById(R.id.five);
        button6 = findViewById(R.id.six);
        button7 = findViewById(R.id.seven);
        button8 = findViewById(R.id.eight);
        button9 = findViewById(R.id.nine);
        devide = findViewById(R.id.devide);
        buttonmultiply = findViewById(R.id.multiplybtn);
        buttonplus = findViewById(R.id.plus);
        buttonminus = findViewById(R.id.minus);
        equal = findViewById(R.id.equally);
        clear = findViewById(R.id.clear);
        op1 = findViewById(R.id.op1);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        devide.setOnClickListener(this);
        buttonmultiply.setOnClickListener(this);
        equal.setOnClickListener(this);
        buttonminus.setOnClickListener(this);
        buttonplus.setOnClickListener(this);
        clear.setOnClickListener(this);
        resultstring = findViewById(R.id.result);
        op1 = findViewById(R.id.op1);
        spref = getPreferences(MODE_PRIVATE);
        String saved = loadvalue();
        if (saved != null) {
            op1.setText(saved);
        }



        calculator = new Calculator();

    }

    Button equal;
    Calculator calculator;
    TextView op1;
    TextView resultstring;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button button0;
    Button clear;
    Button buttonplus;
    Button buttonminus;
    Button buttonmultiply;
    Button devide;
    SharedPreferences spref;

    void savestoryvalue(String value) {
        SharedPreferences.Editor editor = spref.edit();
        editor.putString("result",value);
        editor.commit();
    }
    String loadvalue() {
        return spref.getString("result",null);
    }

    @Override
    public void onClick(View v) {
        if (((Button)v).getText().toString().equals("+")){
            calculator.operation = "+";
            calculator.operant1 = Double.valueOf(resultstring.getText().toString());
            op1.setText(resultstring.getText().toString());
            resultstring.setText("0");
        }
        char c = ((Button)v).getText().toString().charAt(0);
        if (((Button)v).getText().toString().equals("C")){
            resultstring.setText("");
            op1.setText("");
        }
        if (((Button)v).getText().toString().equals("-")){
            calculator.operation = "-";
            calculator.operant1 = Double.valueOf(resultstring.getText().toString());
            op1.setText(resultstring.getText().toString());
            resultstring.setText("0");
        }
        if (((Button)v).getText().toString().equals("×")) {
            calculator.operation = "×";
            calculator.operant1 = Double.valueOf(resultstring.getText().toString());
            op1.setText(resultstring.getText().toString());
            resultstring.setText("0");
        }
        Log.d("2",((Button)v).getText().toString());
        if (((Button)v).getText().toString().equals("÷")) {
            Log.d("1",((Button)v).getText().toString());
            calculator.operation = "/";
            calculator.operant1 = Double.valueOf(resultstring.getText().toString());
            op1.setText(resultstring.getText().toString());
            resultstring.setText("0");
        }
        if (Character.isDigit(c)){
            if (resultstring.getText().toString().equals("0")){
                resultstring.setText("");
                resultstring.setText(resultstring.getText().toString() + String.valueOf(c));
            }
            else {
                resultstring.setText(resultstring.getText().toString() + String.valueOf(c));
            }

        }
        if (((Button)v).getText().toString().equals("=")){
            if (calculator.operant1 != null) {
                Log.d("wwww",calculator.operation);
                if (calculator.operation == ("+")) {
                    calculator.operant1 = calculator.operant1 + Double.valueOf(resultstring.getText().toString());
                    op1.setText(calculator.operant1.toString());
                }
                if (calculator.operation == ("-")) {
                    calculator.operant1 = calculator.operant1 - Double.valueOf(resultstring.getText().toString());
                    op1.setText(calculator.operant1.toString());
                }
                if (calculator.operation == ("×")) {
                    calculator.operant1 = calculator.operant1 * Double.valueOf(resultstring.getText().toString());
                    op1.setText(calculator.operant1.toString());
                }
                if (calculator.operation == ("/")) {
                    calculator.operant1 = calculator.operant1 / Double.valueOf(resultstring.getText().toString());
                    op1.setText(calculator.operant1.toString());
                }
                String str = op1.getText().toString();
                String[] array = str.split("\\.");
                Log.d("3", array.toString());
                if (array.length > 1) {
                    if (array[1].equals("0")) {
                        op1.setText(array[0]);
                    }
                    else {
                        op1.setText(str);
                    }
                }
                savestoryvalue(op1.getText().toString());
            }
        }
    }
}
