package com.example.noteapp.subject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;


@SpringBootTest
public class SubjectControllerTests {
    @Autowired
    private SubjectController subjectController;

    int flag = 1; // 1 = True


    @Test
    public void getAllSubjectsTest(){
        List<Subject> subjects = subjectController.getAllSubjects();

        for (int i = 0; i < subjects.size(); i++) {
            assertThat(subjects.get(i).getClass(), is(Subject.class));
        }

    }

    @Test
    public void getSubjectTest(){
        String id = "64590b68f7cf423233706732";
        Subject subjects = subjectController.getSubject(id);
        assertThat(subjects.getId(), is(id));
    }

    @Test
    public void addSubjectTest(){
        Subject subjects = new Subject("64590b68f7cf423233706742","addtest","add one data");
        int num = subjectController.addSubject(subjects);
        assertThat(num, is(flag));
    }

    @Test
    public void updateSubjectTest(){
        Subject subjects = new Subject("64590b68f7cf423233706732","addtest111","add one data");
        int num = subjectController.updateSubject(subjects, subjects.getId());

        assertThat(num, is(flag));
    }

    @Test
    public void deleteSubjectTest(){
        String id = "64590b68f7cf423233706732";
        int num = subjectController.deleteSubject(id);
        assertThat(num, is(flag));
    }
}
