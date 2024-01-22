package com.quizApp.quizApp.controller;

import com.quizApp.quizApp.model.UserAnswer;
import com.quizApp.quizApp.model.wappers.QuestionWrapper;
import com.quizApp.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<Object> createQuiz(@RequestParam String category,@RequestParam int numOfQuestions){
        return new ResponseEntity<>(quizService.createQuiz(category,numOfQuestions).getStatusCode());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<UserAnswer> answers){
        return quizService.calculateResult(id,answers);
    }
}
