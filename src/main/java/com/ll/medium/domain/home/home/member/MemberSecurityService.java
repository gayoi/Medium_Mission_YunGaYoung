package com.ll.medium.domain.home.home.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Java
        // NPE(Null Pointer Exception)
        // Optional

        Optional<SiteMember> _siteUser = this.userRepository.findByusername(username);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteMember siteUser = _siteUser.get();

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if ("admin".equals(username)) {
////            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue())); //
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); //
//        } else {
//            authorities.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
//        }
//        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);

        return new User(siteUser.getUsername(), siteUser.getPassword(), siteUser.getAuthorities());

    }
}