package com.application.week7complete.data;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//To represent SQLite Table
@Entity(tableName = "student_table") //Annotation to make it meaninful to room database
public class Student implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    @ColumnInfo(name = "name")
    String name;
    @NonNull
    @ColumnInfo(name = "description")
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Student(@NonNull String name, String description) {
//        this.name = name;
//        this.description = description;
//    }

    public Student(int id, @NonNull String name, @NonNull String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


}
