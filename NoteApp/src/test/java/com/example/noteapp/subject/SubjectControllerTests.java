package com.example.noteapp.subject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SubjectControllerTests {
    @Autowired
    private SubjectController subjectController;



    @Test
    public void getAllSubjectsTest(){
        List<Subject> subjects = subjectController.getAllSubjects();
        System.out.println("");

        for (int i = 0; i < subjects.size(); i++) {
            Subject subject = subjects.get(i);
            System.out.println(subject.toString());
        }
        System.out.println("");
    }

    @Test
    public void getSubjectTest(){
        String id = "64590b68f7cf423233706728";
        Subject subjects = subjectController.getSubject(id);
        System.out.println("");
        System.out.println(subjects.toString());
        System.out.println("");
    }

    @Test
    public void addSubjectTest(){
        Subject subjects = new Subject("64590b68f7cf423233706722","addtest","add one data");
        int num = subjectController.addSubject(subjects);
        System.out.println("");
        System.out.println(num);
        System.out.println("");
    }

    @Test
    public void updateSubjectTest(){

    }

    @Test
    public void deleteSubjectTest(){
        String id = "64590b68f7cf423233706722";
        int num = subjectController.deleteSubject(id);
        System.out.println("");
        System.out.println(num);
        System.out.println("");
    }
}
