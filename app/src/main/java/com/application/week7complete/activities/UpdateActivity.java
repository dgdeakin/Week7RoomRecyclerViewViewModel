package com.application.week7complete.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.application.week7complete.R;
import com.application.week7complete.data.Student;
import com.application.week7complete.model.StudentViewModel;

public class UpdateActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText;
    Button updateButton;
    StudentViewModel mStudentViewModel;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameEditText = findViewById(R.id.editTextTextPersonName);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        updateButton = findViewById(R.id.button);

        student = (Student) getIntent().getSerializableExtra("model");
        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        nameEditText.setText(student.getName());
        emailEditText.setText(student.getDescription());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
//                Student student = new Student(0,nameEditText.getText().toString(),
//                        emailEditText.getText().toString());toString
//                mStudentViewModel.update(student);
//                Intent intent = new Intent();
                int id = student.getId();
                String nameValue = nameEditText.getText().toString();
                String descValue = emailEditText.getText().toString();
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("update_value1", nameValue);
                intent.putExtra("update_value2", descValue);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }
}