package com.ll.medium.domain.home.home.member.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject); // like '%:subject%'
    // isPublished  == true

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
    Page<Question> findByIsPublishedTrueOrderByCreateDateDesc(Pageable pageable);

    Page<Question> findBySubjectLikeAndIsPublishedTrueOrderByCreateDateDesc(String subject, Pageable p);


    @Query("select "
            + "distinct q "
            + "from Question q "
            + "left outer join SiteMember u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join SiteMember u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.username like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.username like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}