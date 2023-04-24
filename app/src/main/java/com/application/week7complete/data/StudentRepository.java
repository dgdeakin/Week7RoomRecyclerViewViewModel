package com.application.week7complete.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

//A Repository class abstracts access to multiple data sources.
//A Repository class provides a clean API for data access
// to the rest of the application.
/*
A Repository manages queries and allows you to use multiple backends.
In the most common example, the Repository implements the
logic for deciding whether to fetch data from a network or
use results cached in a local database.
 */
public class StudentRepository {
    StudentDAO mStudentDAO;
    LiveData<List<Student>> studentsList;


    /*
    The DAO is passed into the repository constructor as
    opposed to the whole database. This is because you only
    need access to the DAO, since it contains all the
    read/write methods for the database. There's no need to
    expose the entire database to the repository
     */
    public StudentRepository(Application application) {
        StudentRoomDatabase db = StudentRoomDatabase.getDatabase(application);
        mStudentDAO = db.studentDAO();
        studentsList = mStudentDAO.getStudents();

    }

    //Return LiveData list of students
    public LiveData<List<Student>> getStudents() {
        return studentsList;
    }

    //We need to not run the insert on the main thread,
    // so we use the ExecutorService we created in the
    // WordRoomDatabase to perform the insert on a background thread.
    public void insert(Student student){
        StudentRoomDatabase.databaseWriteExecutor.execute(()->{
            mStudentDAO.insert(student);
        });
    }

    public void update(Student student){
        StudentRoomDatabase.databaseWriteExecutor.execute(()->{
            mStudentDAO.update(student);
        });
    }


    public void delete(Student student){
        StudentRoomDatabase.databaseWriteExecutor.execute(()->{
            mStudentDAO.delete(student);
        });
    }
}
