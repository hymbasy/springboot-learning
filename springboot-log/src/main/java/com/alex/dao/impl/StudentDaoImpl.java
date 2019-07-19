package com.alex.dao.impl;

import com.alex.dao.StudentDao;
import com.alex.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@CacheConfig(cacheNames = "student")
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int update(Student student) {
        String sql = "update student set sname = ?,ssex = ? where sno = ?";
        return jdbcTemplate.update(sql, new Object[]{student.getName(), student.getSex(), student.getSno()});
    }

    @Override
    public void deleteStudentBySno(String sno) {
        String sql = "delete from student where sno = ?";
        jdbcTemplate.update(sql, new Object[]{sno});

    }

    @Override
    public Student queryStudentBySno(String sno) {
        String sql = "select * from student where sno = ?";
        List<Student> studentList = jdbcTemplate.query(sql, new Object[]{sno}, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setName(resultSet.getString("sname"));
                student.setSex(resultSet.getString("ssex"));
                student.setSno(resultSet.getString("sno"));
                return student;

            }
        });
        if (!CollectionUtils.isEmpty(studentList)) {
            return studentList.get(0);
        }
        return null;
    }
}
