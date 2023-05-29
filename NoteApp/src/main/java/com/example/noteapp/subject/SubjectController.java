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
    public int addSubject(@RequestBody Subject subject){
        int num = subjectService.addSubject(subject);
        return num;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subjects/{id}")
    public int updateSubject(@RequestBody Subject subject, @PathVariable String id){
        int num = subjectService.updateSubject(id, subject);
        return num;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/subjects/{id}")
    public int deleteSubject(@PathVariable String id){

        int num = subjectService.deleteSubject(id);
        return num;
    }

}

