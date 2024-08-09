package mit.iwrcore.IWRCore.security.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Contract;
import mit.iwrcore.IWRCore.entity.MemberRole;
import mit.iwrcore.IWRCore.entity.PartS;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Getter
@Setter
@ToString
@Builder
public class PartnerDTO {
    private Long pno;  // 파트너 번호
    private String name;  // 이름
    private String registrationNumber;  // 등록번호
    private String location;  // 위치
    private String type;  // 유형
    private String sector;  // 업종
    private String ceo;  // CEO
    private String telNumber;  // 전화번호
    private String faxNumber;  // 팩스번호
    private String email;  // 이메일
    private String contacter;  // 담당자
    private String contacterNumber;  // 담당자 연락처
    private String contacterEmail;  // 담당자 이메일
    private String id;  // ID
    private String pw;  // 비밀번호
    private String password;  // 비밀번호

    private PartSDTO partSDTO;

    private Set<MemberRole> roleSet = new HashSet<>();

}
