package com.example.numbersystemconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private int spinOption;
    private EditText viewInputNumber;
    private TextView resultView;
    private TextView resultHeadView;
    private Converter convert;
    private Utils util;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewInputNumber = findViewById(R.id.number_input);
        resultView = findViewById(R.id.result_field);
        resultHeadView = findViewById(R.id.result_head);
        convert = new Converter();
        util = new Utils();

        if (savedInstanceState != null) {
            // If result head view is visible, get stored values from bundle and show
            boolean isResultHeadVisible = savedInstanceState.getBoolean("result_visible");
            if (isResultHeadVisible) {
                showResult(savedInstanceState.getString("result_text"));
            }
        }

        String[] numberSystems = {"Binary System", "Octa System", "HexaDecimal System"};
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, numberSystems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the values of the result if the Result Head is visible
        if (resultHeadView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("result_visible", true);
            outState.putString("result_text", resultView.getText().toString());
        }
    }

    public void calculate(View view) {

        try {
            double userInput;
            String wholeNumberPartResult = "0";
            String fractionPartResult = "0";
            userInput = getUserInput(viewInputNumber);
            int wholeNumberPart = Integer.parseInt(util.spiltInt(userInput));
            double fractionPart = Double.parseDouble(util.splitFraction(userInput));
            switch (spinOption) {
                case 0:
                    wholeNumberPartResult = convert.wholeNumberPartConverter(wholeNumberPart, 2);
                    fractionPartResult = convert.decimalPartConverter(fractionPart, 2);
                    break;
                case 1:
                    wholeNumberPartResult = convert.wholeNumberPartConverter(wholeNumberPart, 8);
                    fractionPartResult = convert.decimalPartConverter(fractionPart, 8);
                    break;
                case 2:
                    wholeNumberPartResult = convert.wholeNumberPartConverter(wholeNumberPart, 16);
                    fractionPartResult = convert.decimalPartConverter(fractionPart, 16);
                    break;
                default:
                    String error = "Error.Retry";
                    showResult(error);
                    break;
            }
            if (fractionPartResult.isEmpty()) {
                showResult(wholeNumberPartResult);
            } else {
                String combinedResult = wholeNumberPartResult + "." + fractionPartResult;
                showResult(combinedResult);
            }
        } catch (Exception e) {
            showResult("Empty Input");
        }
    }

    private void showResult(String value) {
        resultHeadView.setVisibility(View.VISIBLE);
        resultView.setText(value);
        resultView.setVisibility(View.VISIBLE);

    }

    private Double getUserInput(EditText viewInputNumber) {
        String inputText = getUserInputString(viewInputNumber);
        return Double.valueOf(inputText);
    }

    private String getUserInputString(EditText viewInputNumberText) {
        return viewInputNumberText.getText().toString();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinOption = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        spinOption = 0;

    }
}
