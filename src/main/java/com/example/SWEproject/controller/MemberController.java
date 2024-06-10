package com.example.SWEproject.controller;

import com.example.SWEproject.dto.MemberForm;
import com.example.SWEproject.entity.Member;
import com.example.SWEproject.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginPage(){
        return "members/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return "redirect:/login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String loginId, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        Member member = memberRepository.findByLoginId(loginId);
        if (member != null && member.getPassword().equals(password)){
            session.setAttribute("user", member);
            return "redirect:/assignments";
        }
        redirectAttributes.addFlashAttribute("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
        redirectAttributes.addFlashAttribute("successMessage", false);
        redirectAttributes.addFlashAttribute("warningMessage", true);
        return "redirect:/login";
    }

    @GetMapping("/signup/agree")
    public String signUpAgreePage(){
        return "members/loginAgree";
    }

    @GetMapping("/signup/form")
    public String signUpPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm, Model model, RedirectAttributes redirectAttributes){
        boolean existEmail = memberRepository.existsByEmail((memberForm.getEmail()));
        boolean existLoginId = memberRepository.existsByLoginId((memberForm.getLoginId()));
        if (!existEmail && !existLoginId) {
            Member member = memberForm.toEntity();
            Member saved = memberRepository.save(member);
            redirectAttributes.addFlashAttribute("message", "회원 가입이 완료 되었습니다.");
            redirectAttributes.addFlashAttribute("successMessage", true);
            redirectAttributes.addFlashAttribute("warningMessage", false);
            return "redirect:/login"; //회원 가입 완료시 로그인 화면으로 이동
        } else {
            model.addAttribute("existEmail", existEmail);
            model.addAttribute("existLoginId", existLoginId);
            model.addAttribute("memberForm", memberForm);
            model.addAttribute("isProfessor", memberForm.getMemberType().equals("professor"));
            return "members/newRe"; //존재하는 이메일이나 아이디인 경우 재입력 페이지 이동
        }
    }
}
