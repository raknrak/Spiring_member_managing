package kr.co.chunjae.repository;

import kr.co.chunjae.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Log4j2
public class MemberRepository {
    private final SqlSessionTemplate sql; // 의존성 주입(마이바티스)

    public int save(MemberDTO memberDTO) {
        log.info("memberDTO = ");
        log.info(memberDTO);
        // Mapper의 namespace : Member. id : save ,
        return sql.insert("Member.save", memberDTO); // 매퍼 호출 후 DTO 객체를 넘긴다.
    }

    public MemberDTO login(MemberDTO memberDTO) {

        return sql.selectOne("Member.login", memberDTO);
    }

    public List<MemberDTO> findAll() {
        return sql.selectList("Member.findAll");
    }

    public MemberDTO findById(Long id) {
        return sql.selectOne("Member.findById",id); // id 요청
    }

    public void delete(Long id) {
        sql.delete("Member.delete", id);
    }

    public MemberDTO findByMemberEmail(String loginEmail) {
        return sql.selectOne("Member.findByMemberEmail", loginEmail);
    }

    public int update(MemberDTO memberDTO) {
        // 1 : update 처리 됨 , 0 : update 처리 안됨 그래서 int로 받음
        return sql.update("Member.update", memberDTO);
    }
}
