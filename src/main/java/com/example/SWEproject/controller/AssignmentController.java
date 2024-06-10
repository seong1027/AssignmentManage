package com.example.SWEproject.controller;

import com.example.SWEproject.dto.AssignmentForm;
import com.example.SWEproject.dto.SubmissionForm;
import com.example.SWEproject.entity.*;
import com.example.SWEproject.repository.AssignmentRepository;
import com.example.SWEproject.repository.SubmissionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AssignmentController {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private SubmissionRepository submissionRepository;

    @GetMapping("/assignments")
    public String assignmentIndex(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        Member loggedInUser = (Member) session.getAttribute("user");
        List<Assignment> assignmentEntityList = assignmentRepository.findAll();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("assignmentList", assignmentEntityList);
        model.addAttribute("isProfessor", loggedInUser instanceof Professor);
        return "assignments/index";
    }

    @GetMapping("/assignments/new")
    public String assignmentEnroll(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        return "assignments/new"; //assignments.
    }

    @PostMapping("/assignments/create")
    public String assignmentCreate(HttpServletRequest request, AssignmentForm form){
        HttpSession session = request.getSession(false);
        Member loggedInUser = (Member) session.getAttribute("user");
        form.setCid(loggedInUser.getId());
        Assignment assignment = form.toEntity();
        Assignment saved = assignmentRepository.save(assignment);
        return "redirect:/assignments/" + saved.getId();
    }

    @GetMapping("/assignments/{assignmentId}")
    public String assignmentShow(HttpServletRequest request, @PathVariable Long assignmentId, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        Assignment assignmentEntity = assignmentRepository.findById(assignmentId).orElse(null);
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("assignment", assignmentEntity);
        model.addAttribute("isProfessor", loggedInUser instanceof Professor);
        return "assignments/show";
    }

    @GetMapping("/assignments/{assignmentId}/edit")
    public String assignmentEdit(HttpServletRequest request, @PathVariable Long assignmentId, Model model){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        //DB에서 수정할 데이터 가져오기
        Assignment assignmentEntity = assignmentRepository.findById(assignmentId).orElse(null);
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("assignment", assignmentEntity);
        model.addAttribute("isProfessor", loggedInUser instanceof Professor);
        return "assignments/edit";
    }

    @PostMapping("/assignments/update")
    public String assignmentUpdate(HttpServletRequest request, AssignmentForm form) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        Member loggedInUser = (Member) session.getAttribute("user");
        Assignment assignmentEntity = form.toEntity();
        Assignment target = assignmentRepository.findById(assignmentEntity.getId()).orElse(null);
        if (target != null){
            assignmentEntity.setCid(loggedInUser.getId());
            assignmentRepository.save(assignmentEntity);
        }
        return "redirect:/assignments/" + assignmentEntity.getId();
    }

    @GetMapping("/assignments/{assignmentId}/submission")
    public String assignementSubmit(HttpServletRequest request, @PathVariable Long assignmentId, Model model){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        Assignment assignmentEntity = assignmentRepository.findById(assignmentId).orElse(null);
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("assignment", assignmentEntity);
        return "assignments/submission";
    }

    @PostMapping("/submission")
    public String submissionComplete(SubmissionForm form) {
        Submission submissionEntity = form.toEntity();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedDateTime = now.format(formatter);

        LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        submissionEntity.setDate(parsedDateTime);

        Submission saved = submissionRepository.save(submissionEntity);
        return "redirect:/assignments/" + submissionEntity.getAssignmentId();
    }

    @GetMapping("/assignments/{assignmentId}/delete")
    public String assignmentDelete(@PathVariable Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);

        //assignmentId에 해당하는 submission을 모두 삭제
        List<Submission> submissionEntityList = submissionRepository.findAllByAssignmentId(assignmentId);
        for (int i=0; i< submissionEntityList.size(); i++){
            Long deleteId = submissionEntityList.get(i).getId();
            submissionRepository.deleteById(deleteId);
        }
        return "redirect:/assignments";
    }

    @GetMapping("/assignments/{assignmentId}/eval")
    public String indexEval(HttpServletRequest request, @PathVariable Long assignmentId, Model model){
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        List<Submission> submissionEntityList = submissionRepository.findAllByAssignmentId(assignmentId);
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("submissionList", submissionEntityList);
        return "assignments/indexEval";
    }

    @GetMapping("/assignments/{assignmentId}/eval/{submissionId}")
    public String assignmentEval(HttpServletRequest request, @PathVariable Long assignmentId, @PathVariable Long submissionId, Model model) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "members/login";
        }
        Assignment assignmentEntity =  assignmentRepository.findById(assignmentId).orElse(null);
        Submission submissionEntity = submissionRepository.findById(submissionId).orElse(null);
        Member loggedInUser = (Member) session.getAttribute("user");
        model.addAttribute("user", loggedInUser);
        model.addAttribute("assignment", assignmentEntity);
        model.addAttribute("submission", submissionEntity);
        return "assignments/eval";
    }

    @PostMapping("/evalComplete")
    public String evalComplete(SubmissionForm form) {
        Submission submissionEntity = form.toEntity();
        Submission target = submissionRepository.findById(submissionEntity.getId()).orElse(null);
        if (target != null){
            submissionEntity.setScore(form.getScore());
            submissionRepository.save(submissionEntity);
        }
        return "redirect:/assignments/" + submissionEntity.getAssignmentId() + "/eval";
    }
}
