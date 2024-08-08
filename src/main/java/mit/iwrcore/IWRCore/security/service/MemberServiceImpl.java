package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<MemberDTO> findMemberList() {
        List<MemberDTO> list=new ArrayList<>();
        memberRepository.findMemberList().forEach(x->list.add(memberTodto(x)));
        return list;
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

    }
}
