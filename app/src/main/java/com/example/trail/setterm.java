package com.example.trail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class setterm extends AppCompatActivity {

    private EditText editText1,editText2,editText3;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setterm);
        editText1 = (EditText) findViewById(R.id.setyear);
        editText2 = (EditText) findViewById(R.id.setmonth);
        editText3 = (EditText)findViewById(R.id.setday);
        btn=(Button)findViewById(R.id.assure);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle year=new Bundle();
                year.putString("fdYear",editText1.getText().toString());
                intent.putExtras(year);
                Bundle month=new Bundle();
                month.putString("fdMonth",editText2.getText().toString());
                intent.putExtras(month);
                Bundle day=new Bundle();
                day.putString("fdDay",editText3.getText().toString());
                intent.putExtras(day);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

    }
}
