package com.quizApp.quizApp.dao;

import com.quizApp.quizApp.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends CrudRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "select * from question q where q.category=:category order by RANDOM() LIMIT :numOfQuestions",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numOfQuestions);
}
