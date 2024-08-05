package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

//    @OneToMany(mappedBy = "partner", fetch = FetchType.LAZY)  // Contract와의 1대다 관계
//    private List<Contract> contracts;  // 이 Partner와 연관된 Contract 목록

    @PostPersist
    public void generateId() {
        if (this.id == null) {
            String temp = "partner" + pno + "_" + registrationNumber.substring(7);
            this.id = temp;
        }
    }

    public void setPartnerRole(MemberRole partnerRole) {
        roleSet.clear();
        roleSet.add(partnerRole);
    }
}
