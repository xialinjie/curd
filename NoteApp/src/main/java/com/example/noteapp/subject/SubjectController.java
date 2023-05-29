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
    public String addSubject(@RequestBody Subject subject){
        String return_id = subjectService.addSubject(subject);
        return return_id;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/subjects/{id}")
    public String updateSubject(@RequestBody Subject subject, @PathVariable String id){
        String return_id = subjectService.updateSubject(id, subject);
        return return_id;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/subjects/{id}")
    public String deleteSubject(@PathVariable String id){
        String return_id = subjectService.deleteSubject(id);
        return return_id;
    }



}

