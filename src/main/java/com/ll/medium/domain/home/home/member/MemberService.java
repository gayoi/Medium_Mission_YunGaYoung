package com.ll.medium.domain.home.home.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteMember create(String username, String password) {
        SiteMember user = new SiteMember();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public SiteMember getMember(String username) {
        Optional<SiteMember> siteMember = this.userRepository.findByusername(username);
        if (siteMember.isPresent()) {
            return siteMember.get();
        } else {
            throw new DataNotFoundException("sitemember not found");
        }
    }

}
