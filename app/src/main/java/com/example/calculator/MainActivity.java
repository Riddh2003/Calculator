package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button button;
    Boolean lastNumeric = false;
    Boolean lastDot = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
    }
    public void onDigit(View view){
        tv = findViewById(R.id.tv);
        button = findViewById(view.getId());
        tv.append(button.getText());
        lastNumeric = true;
    }
    public void onClear(View view){
        tv.setText("");
        lastDot = false;
        lastNumeric = false;
    }
    public void onDecimalPoint(View view){
        if(lastNumeric && !lastDot){
            tv.append(".");
            lastNumeric = false;
            lastDot = true;
        }
    }

    public void onEqual(View view){
        tv = findViewById(R.id.tv);
        if(lastNumeric){
            String tvValue = tv.getText().toString();
            String prefix = "";
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-";
                    tvValue = tvValue.substring(1);
                }

                if(tvValue.contains("-")){
                    String splitValue[] = tvValue.split("-");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if(!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    Double ans = (Double.parseDouble(one) - Double.parseDouble(two));
                    tv.setText(zeroAfterDot(ans.toString()));
                }
                else if(tvValue.contains("+")){
                    String splitValue[] = tvValue.split("\\+");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if(!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    Double ans = (Double.parseDouble(one) + Double.parseDouble(two));
                    tv.setText(zeroAfterDot(ans.toString()));
                }
                else if(tvValue.contains("x")){
                    String splitValue[] = tvValue.split("x");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if(!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    Double ans = (Double.parseDouble(one) * Double.parseDouble(two));
                    tv.setText(zeroAfterDot(ans.toString()));
                }
                else if(tvValue.contains("/")){
                    String splitValue[] = tvValue.split("/");
                    String one = splitValue[0];
                    String two = splitValue[1];
                    if(!prefix.isEmpty()){
                        one = prefix + one;
                    }
                    Double ans = (Double.parseDouble(one) / Double.parseDouble(two));
                    tv.setText(zeroAfterDot(ans.toString()));
                }
            }catch (ArithmeticException e)
            {
                e.printStackTrace();
            }
        }
    }
    private String zeroAfterDot(String result){
        String value = result;
        if(result.contains(".0")){
            value = result.substring(0,result.length()-2);
        }
        return value;
    }
    public void onOperator(View view){
        tv = findViewById(R.id.tv);
        button = findViewById(view.getId());
        if(lastNumeric && !isOperatorAdded(tv.getText().toString())){
            tv.append(button.getText());
            lastNumeric = false;
            lastDot = false;
        }
    }
    private boolean isOperatorAdded(String value){
        if(value.startsWith("-")){
            return false;
        }else {
            return value.contains("x") || value.contains("/") || value.contains("+") || value.contains("-");
        }
    }
}