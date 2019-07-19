package com.alex.service.impl;

import com.alex.dao.StudentDao;
import com.alex.domain.Student;
import com.alex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student update(Student student) {
        studentDao.update(student);
        return studentDao.queryStudentBySno(student.getSno());
    }

    @Override
    public void deleteStudentBySno(String sno) {
        studentDao.deleteStudentBySno(sno);
    }

    @Override
    public Student queryStudentBySno(String sno) {
        return studentDao.queryStudentBySno(sno);
    }
}
