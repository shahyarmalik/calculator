package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public String str = "";  // To hold the input string (the current number)
    char op = 'x';  // To hold the current operator
    float num1 = 0, num2 = 0;  // Variables for the two numbers
    TextView ShowResult;

    Button one, two, three, four, five, six, seven, eight, nine, zero, add, equal, sub, mul, div, clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ShowResult = findViewById(R.id.result_id);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        add = findViewById(R.id.add);
        equal = findViewById(R.id.equal);
        sub = findViewById(R.id.sub);
        mul = findViewById(R.id.mul);
        div = findViewById(R.id.div);
        clear = findViewById(R.id.clear);

        // Define the OnClickListener for all number buttons
        View.OnClickListener numberClickListener = v -> {
            // Get the button's text (which is the number) and append it to the string
            String number = ((Button) v).getText().toString();
            str += number;

            // Update the result display
            ShowResult.setText(str);
        };

        // Attach the listener to each number button
        one.setOnClickListener(numberClickListener);
        two.setOnClickListener(numberClickListener);
        three.setOnClickListener(numberClickListener);
        four.setOnClickListener(numberClickListener);
        five.setOnClickListener(numberClickListener);
        six.setOnClickListener(numberClickListener);
        seven.setOnClickListener(numberClickListener);
        eight.setOnClickListener(numberClickListener);
        nine.setOnClickListener(numberClickListener);
        zero.setOnClickListener(numberClickListener);

        // Define a single OnClickListener for operator buttons
        View.OnClickListener operatorClickListener = v -> {
            // Parse the first number (num1) from the input string when the operator is pressed
            if (!str.isEmpty()) {
                num1 = Float.parseFloat(str);
                str = "";  // Clear the input string to get the next number
            }

            // Get the id of the clicked button and set the operator
            if (v.getId() == R.id.add) {
                op = '+';
            } else if (v.getId() == R.id.sub) {
                op = '-';
            } else if (v.getId() == R.id.mul) {
                op = '*';
            } else if (v.getId() == R.id.div) {
                op = '/';
            }
        };

        // Attach the listener to each operator button
        add.setOnClickListener(operatorClickListener);
        sub.setOnClickListener(operatorClickListener);
        mul.setOnClickListener(operatorClickListener);
        div.setOnClickListener(operatorClickListener);

        // Set the OnClickListener for the equal button
        equal.setOnClickListener(v -> {
            if (!str.isEmpty()) {
                num2 = Float.parseFloat(str);  // Parse the second number (num2) from the input string
                float result = 0;

                // Perform the operation based on the operator
                switch (op) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            ShowResult.setText("Error");  // Handle division by zero
                            return;
                        }
                        break;
                }

                // Display the result and reset the input string
                ShowResult.setText(String.valueOf(result));
                str = "";  // Reset the input for future operations
                num1 = result;  // Store the result as num1 in case the user wants to chain operations
            }
        });

        // Set the OnClickListener for the clear button
        clear.setOnClickListener(v -> {
            str = "";
            num1 = 0;
            num2 = 0;
            op = 'x';
            ShowResult.setText("");  // Clear the display
        });
    }
}
