package com.application.week7complete.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.application.week7complete.R;
import com.application.week7complete.data.Student;
import com.application.week7complete.listener.AdapterListener;
import com.application.week7complete.model.StudentViewModel;
import com.application.week7complete.recyclerview.StudentListAdapter;
import com.application.week7complete.recyclerview.StudentViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    private StudentViewModel mStudentViewModel;
    FloatingActionButton floatingActionButton;

    StudentListAdapter listAdapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        floatingActionButton = findViewById(R.id.fab);


        mStudentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        listAdapter = new StudentListAdapter(new StudentListAdapter.StudentDiff(), this, mStudentViewModel);

        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Student student = new Student(0, data.getStringExtra("add_value1"),
                        data.getStringExtra("add_value2"));
                mStudentViewModel.insert(student);
            }
            else {
                Toast.makeText(MainActivity.this, "Not SAVED", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode == 2){
            if(resultCode == RESULT_OK){
                Student student =  new Student(Integer.parseInt(data.getStringExtra("id")),
                        data.getStringExtra("update_value1"),
                        data.getStringExtra("update_value2"));
                mStudentViewModel.update(student);
                Toast.makeText(MainActivity.this, "UPDATED", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(MainActivity.this, "Not UPDATED", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mStudentViewModel.getStudents().observe(this, students -> {
            //update the cached copy of the words in the adapter
            listAdapter.submitList(students);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.update:
               //update
                return true;
            case R.id.settings:
               //settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}