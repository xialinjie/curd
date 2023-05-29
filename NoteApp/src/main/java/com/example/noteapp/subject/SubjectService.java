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
    public String addSubject(Subject subject){

        String id = subjectRepository.save(subject).getId();

        return id;
    }
    public String updateSubject(String id, Subject subject){

        Query query=new Query(Criteria.where("id").is(subject.getId()));
        Update update= new Update().set("name", subject.getName()).set("description", subject.getDescription());
        mongoTemplate.updateFirst(query,update,Subject.class);
        return id;
    }
    public String deleteSubject(String id){

        subjectRepository.deleteById(id);

        return id;
    }

}

