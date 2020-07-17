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
    private Converter convert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewInputNumber = findViewById(R.id.number_input);
        resultView = findViewById(R.id.result_field);
        convert = new Converter();


        String[] numberSystems = {"Binary System", "Octa System", "HexaDecimal System"};
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, numberSystems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


    }

    public void calculate(View view) {
        int userInput;
        String result;


        try{

            userInput = getUserInput(viewInputNumber);

            switch(spinOption){
                case 0:

                    result = convert.convert(userInput,2);

                    break;
                case 1:
                    result = convert.convert(userInput,8);
                    break;
                case 2:
                    result = convert.convert(userInput,16);
                    break;
                default:
                    result = "Error.Retry";
                    break;

            }


        }catch(Exception e){

            result = "Empty input";
        }

        resultView.setText(result);

    }

    private int getUserInput(EditText viewInputNumber) {
        String inputText = getUserInputString(viewInputNumber);
        return Integer.valueOf(inputText);
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
