package com.ll.medium.domain.home.home.member.answer;


import com.ll.medium.domain.home.home.member.SiteMember;
import com.ll.medium.domain.home.home.member.question.Question;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteMember author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteMember> voter;
}
