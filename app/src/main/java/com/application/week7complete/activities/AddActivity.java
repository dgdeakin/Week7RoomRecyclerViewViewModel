package com.application.week7complete.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.application.week7complete.R;
import com.application.week7complete.data.Student;
import com.application.week7complete.model.StudentViewModel;

public class AddActivity extends AppCompatActivity {


    EditText id, name, description;
    Button add;

//    private StudentViewModel mStudentViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = findViewById(R.id.editTextName);
        description = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.button);
//        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                if(TextUtils.isEmpty(name.getText())){
                    setResult(RESULT_CANCELED, intent);
                }
                else {

                    Student student = new Student(0,name.getText().toString(),
                            description.getText().toString());
//                    mStudentViewModel.insert(student);
                    String nameValue = name.getText().toString();
                    String descValue = description.getText().toString();
                    intent.putExtra("add_value1", nameValue);
                    intent.putExtra("add_value2", descValue);
                    setResult(RESULT_OK, intent);
                }

                finish();

            }
        });
    }
}
