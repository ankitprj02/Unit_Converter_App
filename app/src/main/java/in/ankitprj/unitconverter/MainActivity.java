package in.ankitprj.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner unitSpinner;
    Button convertButton;
    TextView resultView;

    String[] unitTypes = {"Centimeters to Meters", "Meters to Centimeters",
            "Grams to Kilograms", "Kilograms to Grams"};
    String selectedUnit = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        unitSpinner = findViewById(R.id.unitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultView = findViewById(R.id.resultView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, unitTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = unitTypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedUnit = "";
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!inputValue.getText().toString().isEmpty()) {
                    double value = Double.parseDouble(inputValue.getText().toString());
                    double result = 0.0;

                    switch (selectedUnit) {
                        case "Centimeters to Meters":
                            result = value / 100;
                            break;
                        case "Meters to Centimeters":
                            result = value * 100;
                            break;
                        case "Grams to Kilograms":
                            result = value / 1000;
                            break;
                        case "Kilograms to Grams":
                            result = value * 1000;
                            break;
                        default:
                            result = 0.0;
                    }

                    resultView.setText("Result: " + result);
                } else {
                    resultView.setText("Please enter a value.");
                }
            }
        });
    }
}
