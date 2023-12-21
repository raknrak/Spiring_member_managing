package kr.co.chunjae.controller;

import kr.co.chunjae.dto.MemberDTO;
import kr.co.chunjae.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/member") // 공통주소
@RequiredArgsConstructor
@Log4j2
public class MemberController {
    private final MemberService memberService; // 의존성을 주입받고 Service Class 사용

    @GetMapping(value = "/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping(value = "/save")
    //회원가입 정보를 받음
    //@ModelAttribute 필드와 네임값이 같아야함
    public String save(@ModelAttribute MemberDTO memberDTO) { // DTO가 담긴 값의 최종 목적지 : DB
        // 가입이 성공하면 1, 실패하면 0의 값을 반환하기때문.
        int saveResult = memberService.save(memberDTO); // 저장한 정보를 int 타입으로 return 받음
        if (saveResult > 0) {
            return "login"; // 가입 성공
        } else {
            return "save"; // 가입 실패
        }
    }

    @GetMapping(value = "/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        boolean loginResult = memberService.login(memberDTO); // 결과를 DTO 객체로 받아옴
        if (loginResult) {
            session.setAttribute("loginEmail", memberDTO.getMemberEmail());
            return "main"; //ture(로그인 성공)
        } else {
            return "login"; // false(로그인 실패)
        }
    }

    @GetMapping(value = "/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "list";

    }
    // /member?id=1
    @GetMapping
    public String findById(@RequestParam("id") Long id, Model model) { // 데이터를 담기위해 Model 사용
        log.info(id);
        MemberDTO memberDTO = memberService.findById(id); // id값을 넘기고 DTO 객체로 받아옴
        model.addAttribute("member", memberDTO); // 받아온 값을
        return "detail"; // detail.jsp로 출력
    }
}
