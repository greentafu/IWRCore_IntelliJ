package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 직원찾기
    @Override
    public Member findMemberEntity(Long mno, String id) {
        return memberRepository.findMember(mno, id);
    }
    @Override
    public MemberDTO findMemberDto(Long mno, String id) {
        return memberTodto(memberRepository.findMember(mno, id));
    }

    // 직원 리스트
    @Override
    public PageResultDTO<MemberDTO, Member> findMemberList(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("mno").descending());
        Page<Member> entityPage=memberRepository.findAll(pageable);
        Function<Member, MemberDTO> fn=(entity->memberTodto(entity));
        return new PageResultDTO<>(entityPage, fn);
    }
    // 직원 삽입(아이디 중복의 경우 0, 성공시 1)
    @Override
    public Integer insertMember(MemberDTO dto) {
        if(findMemberEntity(null, dto.getId())!=null){
            return 0;
        }
        else{
            Member member=memberdtoToEntity(dto);
            memberRepository.save(member);
            return 1;
        }
    }

    @Override
    public void deleteMember(Long mno) {
        Member member=findMemberEntity(mno, "");
        memberRepository.delete(member);
    }
}
