package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;

public interface MemberService {

    Member findMemberEntity(Long mno, String id);
    MemberDTO findMemberDto(Long mno, String id);

    PageResultDTO<MemberDTO, Member> findMemberList(PageRequestDTO requestDTO);

    Integer insertMember(MemberDTO dto, Long role);

    void deleteMember(Long mno);

    default Member memberdtoToEntity(MemberDTO dto){
        return Member.builder()
                .mno(dto.getMno())
                .department(dto.getDepartment())
                .id(dto.getId())
                .name(dto.getName())
                .phonenumber(dto.getPhonenumber())
                .pw(dto.getPw())
                .password(dto.getPassword())
                .roleSet(dto.getRoleSet())
                .build();
    }
    default MemberDTO memberTodto(Member entity){
        return MemberDTO.builder()
                .mno(entity.getMno())
                .department(entity.getDepartment())
                .id(entity.getId())
                .name(entity.getName())
                .phonenumber(entity.getPhonenumber())
                .pw(entity.getPw())
                .password(entity.getPassword())
                .roleSet(entity.getRoleSet())
                .build();
    }
}
