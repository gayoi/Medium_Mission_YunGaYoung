package com.ll.medium.domain.home.home.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService userService;

    @GetMapping("/join")
    public String signup(MemberCreateForm userCreateForm) {
        return "domain/home/home/join_form"; // 파일 경로로 꼭 적어주기..!!
    }

    @PostMapping("/join")
    public String signup(@Valid MemberCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "domain/home/home/join_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "domain/home/home/join_form";
        }
        try {
            userService.create(userCreateForm.getUsername(),
                     userCreateForm.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "domain/home/home/join_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "domain/home/home/join_form";
        }

        return "redirect:/question/list";
    }

    @GetMapping("/login")
    public String login() {
        return "domain/home/home/login_form";
    }

}