package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inputEditText;
    TextView resultTextView, inputLabel;
    Spinner unitSpinner;

    String[] UNITS = {
            //cm,m,km
            "Centimeters to Meters (m)",
            "Centimeters to Kilometers (km)",
            "Meters to Centimeters (cm)",
            "Meters to Kilometers (km)",
            "Kilometers to Centimeters (cm)",
            "Kilometers to Meters (m)",

            //mg,g,kg
            "Milligrams to Grams (g)",
            "Milligrams to Kilograms (kg)",
            "Grams to Milligrams (mg)",
            "Grams to Kilograms (kg)",
            "Kilograms to Milligrams (g)",
            "Kilograms to Grams (g)"
    };

    double CM_TO_M = 0.01;
    double CM_TO_KM = 1e-5;
    double M_TO_CM = 100.0;
    double M_TO_KM = 0.001;
    double KM_TO_CM = 100000.0;
    double KM_TO_M = 1000.0;

    double MG_TO_G = 0.001;
    double MG_TO_KG = 1e-6;
    double G_TO_MG = 1000.0;
    double G_TO_KG = 0.001;
    double KG_TO_MG = 1e+6;
    double KG_TO_G = 1000.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating Objects
        inputEditText = findViewById(R.id.inputEditText);
        resultTextView = findViewById(R.id.resultTextView);
        inputLabel = findViewById(R.id.inputText1);
        unitSpinner = findViewById(R.id.unitSpinner);

        //Setting Up Array Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, UNITS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Linking Array Adapter
        unitSpinner.setAdapter(adapter);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                convertUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        inputEditText.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // this function is called before text is edited
            // no need
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            convertUnit();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // this function is called after text is edited
            // no need
        }
    };

    public void convertUnit() {
        String inputString = inputEditText.getText().toString().trim();

        if (!inputString.isEmpty()) {
            double inputValue = Double.parseDouble(inputString);

            int selectedUnit = unitSpinner.getSelectedItemPosition();

            double result;
            String resultUnit;

           // length
            if (selectedUnit == 0) {
                inputLabel.setText("Enter CM:");
                result = inputValue * CM_TO_M;
                resultUnit = "m";
            }
            else if (selectedUnit == 1) {
                inputLabel.setText("Enter CM:");
                result = inputValue * CM_TO_KM;
                resultUnit = "km";
            }
            else if (selectedUnit == 2) {
                inputLabel.setText("Enter M:");
                result = inputValue * M_TO_CM;
                resultUnit = "cm";
            }
            else if (selectedUnit == 3) {
                inputLabel.setText("Enter M:");
                result = inputValue * M_TO_KM;
                resultUnit = "km";
            }

            else if (selectedUnit == 4) {
                inputLabel.setText("Enter KM:");
                result = inputValue * KM_TO_CM;
                resultUnit = "cm";
            }
            else if (selectedUnit == 5) {
                inputLabel.setText("Enter KM:");
                result = inputValue * KM_TO_M;
                resultUnit = "m";
            }


            // mass
            else if (selectedUnit == 6) {
                inputLabel.setText("Enter MG:");
                result = inputValue * MG_TO_G;
                resultUnit = "g";
            }
            else if (selectedUnit == 7) {
                inputLabel.setText("Enter MG:");
                result = inputValue * MG_TO_KG;
                resultUnit = "kg";
            }
            else if (selectedUnit == 8) {
                inputLabel.setText("Enter G:");
                result = inputValue * G_TO_MG;
                resultUnit = "mg";
            }
            else if (selectedUnit == 9) {
                inputLabel.setText("Enter G:");
                result = inputValue * G_TO_KG;
                resultUnit = "kg";
            }
            else if (selectedUnit == 10) {
                inputLabel.setText("Enter KG:");
                result = inputValue * KG_TO_MG;
                resultUnit = "mg";
            }
            else if (selectedUnit == 11) {
                inputLabel.setText("Enter KG:");
                result = inputValue * KG_TO_G;
                resultUnit = "g";
            }

            else{
                inputLabel.setText("Enter Data:");
                result = inputValue;
                resultUnit = "";
            }

            String resultString = String.format("%.2f %s", result, resultUnit);
            resultTextView.setText(resultString);
        } else {
            resultTextView.setText("");
        }
    }
}