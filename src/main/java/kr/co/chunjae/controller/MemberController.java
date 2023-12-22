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

    @GetMapping(value = "/delete")
    // 쿼리스트링으로 받으므로 RequestParam
    public String delete(@RequestParam("id") Long id) {
        log.info(id);
        memberService.delete(id); // 삭제처리 수행 후
        // redirect 뒤에는 jsp 이름이 아닌 주소값이 와야함.
        // 삭제되고 새로운 목록을 보여주기 위해 redirect를 사용함.
        return "redirect:/member/"; // 리스트 재요청
    }

    // 수정 화면 요청
    // 누구의 정보를 수정할 것인지 알아야 함
    // 세션에 로그인 정보가 담겨있음
    // 수정화면이라 Form이 들어감
    @GetMapping(value = "/update")
    public String updateForm(HttpSession session, Model model) { // 로그인한 정보 -> session.
        // 세션에 저장된 이메일 가져오기
        // Object(session.getAttribure)를 String으로 형변환
        String loginEmail = (String) session.getAttribute("loginEmail"); // loginEmail parameter 가져옴.
        // loginEmail을 DB로 부터 조회하고 DTO로 가져온 뒤
        MemberDTO memberDTO = memberService.findByMemberEmail(loginEmail);
        model.addAttribute("member", memberDTO);
        return "update"; // update.jsp

    }

    @PostMapping(value = "/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
       boolean result = memberService.update(memberDTO);
        if (result) {
            // 상세주소. DTO에서 id값을 받아오는 것을 요청.
            return "redirect:/member?id=" + memberDTO.getId(); // 성공시
        } else {
            return "index"; // 실패시
        }
    }

    @PostMapping(value = "/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail : " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "index";
    }
}
