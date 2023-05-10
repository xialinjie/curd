package com.example.noteapp.subject;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping(value="hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/subjects")
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @RequestMapping("/subjects/{id}")
    public Subject getSubject(@PathVariable String id){
        return subjectService.getSubject(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/subjects")
    public void addSubject(@RequestBody Subject subject){
        subjectService.addSubject(subject);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subjects/{id}")
    public void updateSubject(@RequestBody Subject subject, @PathVariable String id){
        subjectService.updateSubject(id, subject);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/subjects/{id}")
    public void deleteSubject(@PathVariable String id){
        subjectService.deleteSubject(id);
    }

}

