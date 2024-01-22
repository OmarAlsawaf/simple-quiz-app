package com.quizApp.quizApp.controller;

import com.quizApp.quizApp.model.Question;
import com.quizApp.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions().getBody(), questionService.getAllQuestions().getStatusCode());
    }

    @GetMapping("/category/{topic}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable(name = "topic") String category){
        //return questionService.getQuestionsByCategory(category);
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category).getBody(),questionService.getQuestionsByCategory(category).getStatusCode());
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addQuestion(@RequestBody Question question){
        return new ResponseEntity<>(questionService.addQuestion(question).getStatusCode());
    }
}
