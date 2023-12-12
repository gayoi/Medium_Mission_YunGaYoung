package com.ll.medium.domain.home.home.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteMember create(String username, String email, String password) {
        SiteMember user = new SiteMember();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

}
