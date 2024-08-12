package mit.iwrcore.IWRCore.security.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.entity.QMember;
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

        BooleanBuilder booleanBuilder=getSearch(requestDTO);

        Page<Member> entityPage=memberRepository.findAll(booleanBuilder ,pageable);
        Function<Member, MemberDTO> fn=(entity->memberTodto(entity));

        PageResultDTO pageResultDTO=new PageResultDTO<>(entityPage, fn);
        pageResultDTO.setDepartment(requestDTO.getDepartment());
        pageResultDTO.setRole(requestDTO.getRole());
        pageResultDTO.setMemberSearch(requestDTO.getMemberSearch());

        return pageResultDTO;
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

    // 검색조건
    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        Integer department=requestDTO.getDepartment();
        Integer role= requestDTO.getRole();
        String memberSearch= requestDTO.getMemberSearch();

        BooleanBuilder booleanBuilder=new BooleanBuilder();
        QMember qMember=QMember.member;
        BooleanExpression expression=qMember.mno.gt(0L); // mno>0

        booleanBuilder.and(expression);

        if(department==null && role==null && memberSearch==null){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder1=new BooleanBuilder();
        if(department!=null){
            switch (department){
                case 0: conditionBuilder1.and(qMember.department.contains("자재부서")); break;
                case 1: conditionBuilder1.and(qMember.department.contains("개발부서")); break;
                case 2: conditionBuilder1.and(qMember.department.contains("생산부서"));
            }
        }
        BooleanBuilder conditionBuilder2=new BooleanBuilder();
        if(role!=null){
            switch (role){
                case 0: conditionBuilder2.and(qMember.roleSet.contains(MemberRole.valueOf("MANAGER"))); break;
                case 1: conditionBuilder2.and(qMember.roleSet.contains(MemberRole.valueOf("MATERIAL_TEAM"))); break;
                case 2: conditionBuilder2.and(qMember.roleSet.contains(MemberRole.valueOf("DEV_PROD_TEAM")));
            }
        }
        BooleanBuilder conditionBuilder3=new BooleanBuilder();
        if(memberSearch!=null){
            conditionBuilder3.or(qMember.name.contains(memberSearch)).or(qMember.phonenumber.contains(memberSearch));
        }

        booleanBuilder.and(conditionBuilder1).and(conditionBuilder2).and(conditionBuilder3);
        return booleanBuilder;
    }
}
