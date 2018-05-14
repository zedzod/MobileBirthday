package com.example.trope.myapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class CalculatorActivity extends AppCompatActivity {
    protected double finalResult = 0;
    protected Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,buttonEqual, buttonPlus, buttonDivide, buttonMinus, buttonMultiply, buttonClear;
    protected TextView calculatorLed;
    protected JsEvaluator jsEvaluator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initCalculator();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    protected void initCalculator() {
        calculatorLed = findViewById(R.id.calculatorLed);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonEqual = findViewById(R.id.buttonequal);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonClear = findViewById(R.id.buttonClear);
        jsEvaluator =  new JsEvaluator(this);

        List<Button> NumberedButtons = Arrays.asList(buttonPlus, buttonDivide, buttonMinus, buttonMultiply,button0, button1, button2, button3, button4, button5, button6, button7, button8, button9);
        List<Button> OperButtons = Arrays.asList(buttonEqual, buttonClear);
        for (Button tmpButton : NumberedButtons)
            tmpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String newVal= ((Button) view).getText().toString();
                    String oldVal = calculatorLed.getText().toString();
                    if (oldVal.equals("0")) calculatorLed.setText(newVal);
                    else calculatorLed.setText( oldVal + newVal);
                }
            });
        for (Button tmpButton : OperButtons)
            tmpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String oper= ((Button) view).getText().toString();
                    if (oper.equals("C")) {
                        finalResult=0;
                        calculatorLed.setText("0");
                    }
                    else {
                        jsEvaluator.evaluate(calculatorLed.getText().toString(), new JsCallback() {
                            @Override
                            public void onResult(String result) {
                                finalResult=Double.parseDouble(result);
                                calculatorLed.setText(result);
                            }

                            @Override
                            public void onError(String errorMessage) {
                                calculatorLed.setText( new DecimalFormat("#.##").format(finalResult));

                            }
                        });

                    }
                }
            });

    }
}
