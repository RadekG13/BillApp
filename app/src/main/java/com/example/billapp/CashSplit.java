package com.example.billapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CashSplit extends AppCompatActivity {
    EditText howmuch;
    TextView sum;
    TextView result;
    Double result2;
    Double sum2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_split);

    Button btn=(Button) findViewById(R.id.button4);
         sum=(TextView) findViewById(R.id.textView9);
         result=(TextView) findViewById(R.id.textView8);
         howmuch=(EditText)findViewById(R.id.editText4);
        Bundle bundle=getIntent().getExtras();
        String price=bundle.getString("price");
        sum.setText(price);

        sum2=Double.parseDouble(price);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String howmuch2=howmuch.getEditableText().toString();
                Double podzielic=Double.parseDouble(howmuch2);
                result2=sum2/podzielic;
             //   String newresult=Double.toString(result2);
                String newresult=String.format("%.2f", result2);
                result.setText(newresult);
            }
        });


    }
}
