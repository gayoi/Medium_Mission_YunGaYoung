package com.ll.medium.domain.home.home.member.question;

import com.ll.medium.domain.home.home.member.SiteMember;
import com.ll.medium.domain.home.home.member.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Boolean isPublished;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //mappedBy 외래키랑 연결되어있다
    private List<Answer> answerList;

    @ManyToOne
    private SiteMember author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteMember> voter;

}
