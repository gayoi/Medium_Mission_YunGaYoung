package com.ll.medium.domain.home.home.controller;

import com.ll.medium.domain.home.home.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeController{

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void testJpa() {

    }
}
