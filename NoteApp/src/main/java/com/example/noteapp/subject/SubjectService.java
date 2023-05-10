package com.example.noteapp.subject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Subject> getAllSubjects(){
        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll()
                .forEach(subjects::add);
        return subjects;
    }
    public Subject getSubject(String id){

        return subjectRepository.findById(id).orElse(new Subject());
    }
    public void addSubject(Subject subject){

        subjectRepository.save(subject);
    }
    public void updateSubject(String id, Subject subject){
        subjectRepository.deleteById(id);
        subjectRepository.save(subject);
//        System.out.println(id);
//        System.out.println(subject);
//        Query query=new Query(Criteria.where("id").is(subject.getId()));
//        Update update= new Update().set("name", subject.getName()).set("description", subject.getDescription());
//        mongoTemplate.updateFirst(query,update,MongoTest.class);
    }
    public void deleteSubject(String id){

        subjectRepository.deleteById(id);
    }

}

