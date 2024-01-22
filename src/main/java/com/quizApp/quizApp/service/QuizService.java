package com.quizApp.quizApp.service;

import com.quizApp.quizApp.dao.QuestionDao;
import com.quizApp.quizApp.dao.QuizDao;
import com.quizApp.quizApp.model.Question;
import com.quizApp.quizApp.model.Quiz;
import com.quizApp.quizApp.model.UserAnswer;
import com.quizApp.quizApp.model.wappers.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<Object> createQuiz(String category,Integer numOfQuestions){
        List<Question> quizQuestions = questionDao.findRandomQuestionsByCategory(category,numOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setQuestions(quizQuestions);
        quizDao.save(quiz);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        try{
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> quizQuestions = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();
            for(Question question :quizQuestions){
                questionsForUser.add(new QuestionWrapper(question.getId(),question.getQuestionString(),question.getOption1(), question.getOption2(), question.getOption3(),
                        question.getOption4()));
            }

            return new ResponseEntity<>(questionsForUser,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Integer> calculateResult (Integer id, List<UserAnswer> answers){
        try{
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> quizQuestions = quiz.get().getQuestions();
            int score = 0;
            int questionIndex = 0;
            for(UserAnswer answer:answers){
                if (answer.getAnswer().equals(quizQuestions.get(questionIndex).getAnswer())){
                    score++;
                };
                questionIndex++;
            }
            return new ResponseEntity<>(score,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
