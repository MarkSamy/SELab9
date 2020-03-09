package com.miu.register.demo.controller;

import com.miu.register.demo.model.Student;
import com.miu.register.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = {"eregister/student/list"})
    public ModelAndView listStudents() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("students", studentService.getStudent());
        mav.setViewName("student/list");
        return mav;
    }
    @GetMapping(value = {"eregister/student/add"})
    public String addStudentGet(Model model){
        model.addAttribute("student", new Student());
        return "student/add";
    }
    @PostMapping(value = {"eregister/student/add"})
    public String addStudentPost(@ModelAttribute @Valid Student student){
        studentService.addStudent(student);
        return "redirect:/eregister/student/add";
    }
    @GetMapping(value = {"eregister/student/form"})
    public String studentForm(){
        return "student/add";
    }
}
