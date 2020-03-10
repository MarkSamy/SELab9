package com.miu.register.demo.controller;

import com.miu.register.demo.model.Student;
import com.miu.register.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
    public String addStudentPost(@ModelAttribute("student") Student student){
        studentService.addStudent(student);
        return "redirect:/eregister/student/list";
    }
    @GetMapping(value = {"/eregister/student/delete/{studentId}"})
    public String deleteStudent(@PathVariable long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/eregister/student/list";
    }
    @GetMapping(value = {"/eregister/student/edit/{studentId}"})
    public String editStudent(@PathVariable long studentId, Model model) {
        Student student = studentService.getStudent(studentId);
        if (student != null) {
            model.addAttribute("student", student);
            return "student/edit";
        }
        return "student/list";
    }

    @PostMapping(value = {"/eregister/student/edit"})
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "student/edit";
        }
        studentService.addStudent(student);
        return "redirect:/eregister/student/list";
    }
    @GetMapping(value = {"/eregister/student/search"})
    public ModelAndView searchStudents(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Student> students = studentService.searchStudents(searchString);
        modelAndView.addObject("students", students);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("studentsCount", students.size());
        modelAndView.setViewName("student/list");
        return modelAndView;
    }

}
