package com.application.week7complete.listener;

import com.application.week7complete.data.Student;

public interface AdapterListener {

    void onUpdate(Student student);
    void onDelete(int id, int position);
}
