package com.application.week7complete.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.application.week7complete.data.Student;
import com.application.week7complete.data.StudentRepository;


import java.util.List;

/*
The ViewModel's role is to provide data to the UI and survive configuration changes.
 A ViewModel acts as a communication center between the Repository and the UI.
 You can also use a ViewModel to share data between fragments.
 The ViewModel is part of the lifecycle library.
 */


/*
Created a class called WordViewModel that gets the Application
as a parameter and extends AndroidViewModel.
 */
public class StudentViewModel extends AndroidViewModel {

    /*
    Added a private member variable to hold a reference to the repository.

     */
    StudentRepository mStudentRepository;

    LiveData<List<Student>> students;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        mStudentRepository = new StudentRepository(application);
        students = mStudentRepository.getStudents();

    }


    /*
   Added a getStudents() method to return a cached list of students.

    */
    public LiveData<List<Student>> getStudents() {
        return students;
    }

    /*
    Created a wrapper insert() method that calls the Repository's
    insert() method. In this way, the implementation of insert()
    is encapsulated from the UI.
     */
    public void insert(Student student){
        mStudentRepository.insert(student);
    }

    public void update(Student student){
        mStudentRepository.update(student);
    }

    public void delete(Student student){
        mStudentRepository.delete(student);
    }
}
