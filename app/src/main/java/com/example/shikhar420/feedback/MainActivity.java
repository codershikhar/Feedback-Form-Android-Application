package com.example.shikhar420.feedback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String studentname;
    String studentrollno;
    String subject;
    String teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void nextOnClick(View v)
    {
        EditText editText_name = (EditText) findViewById(R.id.editText_name);
        EditText editText_rollno = (EditText) findViewById(R.id.editText_rollno);
        Spinner spinner_subjects = (Spinner) findViewById(R.id.spinner_subjects);

        studentname = editText_name.getText().toString();
        studentrollno = editText_rollno.getText().toString();
        subject = spinner_subjects.getSelectedItem().toString();

        setContentView(R.layout.activity_feedback);

        TextView name2 = (TextView) findViewById(R.id.textView_name);
        TextView rollno2 = (TextView) findViewById(R.id.textView_rollno);
        TextView subject2 = (TextView) findViewById(R.id.textView_subject);
        TextView teacher2 = (TextView) findViewById(R.id.textView_teacher);

        name2.setText("Student Name :       " + studentname);
        rollno2.setText("Student Roll No. :   " + studentrollno);
        subject2.setText("Teachers Subject : " + subject);

        if(subject.equals("CN"))
        {
            teacher = "Mr. Pratik";
//            teacher2.setText("Teacher Name :      " + "Mr. Pratik");
        }else if(subject.equals("SEPM"))
        {
            teacher = "Ms. Suja Panikar";
//            teacher2.setText("Teacher Name :      " + "Ms. Suja Panikar");
        }else if(subject.equals("SDL"))
        {
            teacher = "Ms. Madhulika";
//            teacher2.setText("Teacher Name :      " + "Ms. Madhulika");
        }else if(subject.equals("DBMS"))
        {
            teacher = "Ms. Shakti Kinger";
//            teacher2.setText("Teacher Name :      " + "Ms. Shakti Kinger");
        }else if(subject.equals("TOC"))
        {
            teacher = "Ms. Ranjana Agrawal";
//            teacher2.setText("Teacher Name :      " + "Ms. Ranjana Agrawal");
        }else
        {
            teacher = "Mr. Pawar";
//            teacher2.setText("Teacher Name :      " + "Mr. Pawar");
        }
        teacher2.setText("Teacher Name :      " + teacher);
    }

    protected void submitOnClick(View v)
    {
        Spinner spinner_question_1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner_question_2 = (Spinner) findViewById(R.id.spinner2);
        Spinner spinner_question_3 = (Spinner) findViewById(R.id.spinner3);
        Spinner spinner_question_4 = (Spinner) findViewById(R.id.spinner4);
        Spinner spinner_question_5 = (Spinner) findViewById(R.id.spinner5);
        Spinner spinner_question_6 = (Spinner) findViewById(R.id.spinner6);
        Spinner spinner_question_7 = (Spinner) findViewById(R.id.spinner7);
        Spinner spinner_question_8 = (Spinner) findViewById(R.id.spinner8);
        Spinner spinner_question_9 = (Spinner) findViewById(R.id.spinner9);

        String spinner_choice_1 = spinner_question_1.getSelectedItem().toString();
        String spinner_choice_2 = spinner_question_2.getSelectedItem().toString();
        String spinner_choice_3 = spinner_question_3.getSelectedItem().toString();
        String spinner_choice_4 = spinner_question_4.getSelectedItem().toString();
        String spinner_choice_5 = spinner_question_5.getSelectedItem().toString();
        String spinner_choice_6 = spinner_question_6.getSelectedItem().toString();
        String spinner_choice_7 = spinner_question_7.getSelectedItem().toString();
        String spinner_choice_8 = spinner_question_8.getSelectedItem().toString();
        String spinner_choice_9 = spinner_question_9.getSelectedItem().toString();

        float avg_rating = 0;
        String s[] = {spinner_choice_1, spinner_choice_2, spinner_choice_3, spinner_choice_4,
                spinner_choice_5, spinner_choice_6, spinner_choice_7, spinner_choice_8,
                spinner_choice_9};

        for(int i = 0; i < 9; i++)
        {
            if(s[i].equals("Strongly Agree")){avg_rating += 5;}
            else if(s[i].equals("Agree")){avg_rating += 4;}
            else if(s[i].equals("Neutral")){avg_rating += 3;}
            else if(s[i].equals("Disagree")){avg_rating += 2;}
            else if(s[i].equals("Strongly Disagree")){avg_rating += 1;}
            else {avg_rating += 0;}
        }

        String avg_rate = Float.toString(avg_rating/9);
        Log.d("--------------->>", avg_rate);

        String type = "insert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, studentrollno, subject, teacher, avg_rate);

        setContentView(R.layout.activity_thankyou);
    }

    protected void exitOnClick(View v){System.exit(0);}

    protected void newFeedOnClick(View v){setContentView(R.layout.activity_main);}
}