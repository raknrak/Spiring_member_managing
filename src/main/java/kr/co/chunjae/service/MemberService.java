package kr.co.chunjae.service;

import kr.co.chunjae.dto.MemberDTO;
import kr.co.chunjae.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository; // 의존성 주입

    public int save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO);
    }

    public boolean login(MemberDTO memberDTO) {
        MemberDTO loginMember =  memberRepository.login(memberDTO);
        if (loginMember != null) {
            return true;
        } else {
            return false;
        }
    }

    public List<MemberDTO> findAll() {
        return memberRepository.findAll();
    }

    public MemberDTO findById(Long id) {
        return memberRepository.findById(id);
    }

    public void delete(Long id) {
        memberRepository.delete(id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return memberRepository.findByMemberEmail(loginEmail);
    }

    public boolean update(MemberDTO memberDTO) {
        int result = memberRepository.update(memberDTO);
        // boolean 처리
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
    public String emailCheck(String memberEmail) {
       MemberDTO memberDTO = memberRepository.findByMemberEmail(memberEmail);
        if (memberDTO == null) {
            return "ok"; // 조회 결과가 없음
        } else {
            return "no"; // 조회 결과가 있음
        }
    }
}
