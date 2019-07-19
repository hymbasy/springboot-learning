package com.alex.controller;

import com.alex.dao.SysLogDao;
import com.alex.domain.Student;
import com.alex.domain.SysLog;
import com.alex.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/listLog")
    public String index(Model model) {
        List<SysLog> sysLogs = sysLogDao.listSysLog();
        model.addAttribute("logList", sysLogs);
        return "account";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/getStudent")
    @ResponseBody
    public List<Student> queryStudent(String sno) {
        List<Student> studentList = new ArrayList<>();
        Student student1 = this.studentService.queryStudentBySno(sno);
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        studentList.add(student1);
        Student student2 = this.studentService.queryStudentBySno(sno);
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
        studentList.add(student2);
        return studentList;
    }

    @RequestMapping("/query")
    @ResponseBody
    public List<Student> query(String no) {
        List<Student> studentList = new ArrayList<>();
        Student student1 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student1.getSno() + "的学生姓名为：" + student1.getName());
        studentList.add(student1);
        student1.setName("康康");
        this.studentService.update(student1);

        Student student2 = this.studentService.queryStudentBySno("001");
        System.out.println("学号" + student2.getSno() + "的学生姓名为：" + student2.getName());
        studentList.add(student2);
        return studentList;
    }
}
