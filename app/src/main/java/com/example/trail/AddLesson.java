package com.example.trail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class AddLesson extends AppCompatActivity {

    private EditText className,classRoom,from,to,wf,wt;
    private Spinner wd;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lesson);
        className=(EditText)findViewById(R.id.className);
        classRoom=(EditText)findViewById(R.id.classRoom);
        from=(EditText)findViewById(R.id.from);
        to=(EditText)findViewById(R.id.to);
        wf=(EditText)findViewById(R.id.wf);
        wt=(EditText)findViewById(R.id.wt);
        wd=(Spinner)findViewById(R.id.weekday);
        add=(Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle ClassName=new Bundle();
                ClassName.putString("className",className.getText().toString());
                intent.putExtras(ClassName);
                Bundle ClassRoom=new Bundle();
                ClassRoom.putString("classRoom",classRoom.getText().toString());
                intent.putExtras(ClassRoom);
                Bundle From=new Bundle();
                From.putString("from",from.getText().toString());
                intent.putExtras(From);
                Bundle To=new Bundle();
                To.putString("to",to.getText().toString());
                intent.putExtras(To);
                Bundle Wf=new Bundle();
                Wf.putString("weekFrom",wf.getText().toString());
                intent.putExtras(Wf);
                Bundle Wt=new Bundle();
                Wt.putString("weekTo",wt.getText().toString());
                intent.putExtras(Wt);
                Bundle weekDay=new Bundle();
                weekDay.putString("WeekDay",wd.getSelectedItem().toString());
                intent.putExtras(weekDay);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
