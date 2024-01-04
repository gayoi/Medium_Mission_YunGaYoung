package com.ll.medium.domain.home.home.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@Entity
public class SiteMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    // 누가 멤버쉽회원인지 구분할 수 가 없다
    private boolean isPaid; // 유료회원 여부 -> 이게 true면 유료회원,false 유료회원

    // Java
    // Generic
    // extends, implements

    // Collections API

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

//        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));

//        if (List.of("admin", "system").contains(username))

//        if (username.equals("admin") || username.equals("system")) {
        if (List.of("admin", "system").contains(username)) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }

        if (isPaid) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_PAID"));
            authorities.add(new SimpleGrantedAuthority(MemberRole.PAID.getValue()));
        }

        return authorities;

    }

}
