package com.application.week7complete.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
A DAO (data access object) validates your SQL at compile-time and associates
it with a method. In your Room DAO, you use handy annotations, like @Insert,
to represent the most common database operations! Room uses the DAO to create a clean
API for your code.
 */


//The DAO must be an interface or abstract class.
// By default, all queries must be executed on a separate thread.
@Dao
public interface StudentDAO {

    /// allowing the insert of the same student multiple times
    // with conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);

    @Update
    void update(Student student);

    @Delete
    void delete(Student student);

    //@Query requires that you provide a SQL query as a string parameter to the annotation.
    @Query("DELETE FROM student_table")
    void deleteAll();

    //A method to get all the students and have it return a List of Students
    @Query("SELECT * FROM student_table ORDER BY name ASC")
//    List<Student> getStudents();
    //LiveData, a lifecycle library class for data observation,
    LiveData<List<Student>> getStudents();
}
