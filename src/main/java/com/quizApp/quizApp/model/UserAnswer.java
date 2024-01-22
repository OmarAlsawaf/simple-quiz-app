package com.quizApp.quizApp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAnswer {

    private Integer id;
    private String answer;

}
