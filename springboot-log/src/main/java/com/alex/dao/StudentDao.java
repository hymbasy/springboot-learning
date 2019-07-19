package com.alex.dao;

import com.alex.domain.Student;

public interface StudentDao {

    int update(Student student);

    void deleteStudentBySno(String sno);

    Student queryStudentBySno(String sno);
}
