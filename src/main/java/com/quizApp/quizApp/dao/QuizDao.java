package com.quizApp.quizApp.dao;

import com.quizApp.quizApp.model.Question;
import com.quizApp.quizApp.model.Quiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizDao extends CrudRepository<Quiz,Integer> {


}
