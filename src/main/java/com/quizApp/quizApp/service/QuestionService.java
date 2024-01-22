package com.quizApp.quizApp.service;

import com.quizApp.quizApp.dao.QuestionDao;
import com.quizApp.quizApp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            return new ResponseEntity<>((List)questionDao.findAll(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<Question>(),HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            if(questionDao.findByCategory(category).isEmpty()){
                return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ArrayList<Question>(),HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<HttpStatus> addQuestion(Question question) {
        try{
            questionDao.save(question);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
