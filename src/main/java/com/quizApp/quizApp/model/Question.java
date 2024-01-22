package com.quizApp.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "question_title")
    private String questionString;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    @Column(name = "right_answer")
    private String answer;
    @Column(name = "difficulty_level")
    private String difficultyLevel;
    private String category;

}
