package com.ll.medium.domain.home.home.controller;

import com.ll.medium.domain.home.home.member.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomeController{

    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 1; i <= 30; i++) {
            String subject = String.format("테스트 데이터 [%03d]", i);
            String content = "내용";
            Boolean isPublished = true;
            this.questionService.create(subject, content, isPublished,null);
        }

    }
}
