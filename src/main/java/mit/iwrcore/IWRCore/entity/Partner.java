package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "partS")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;  // 파트너 번호
    @NotNull
    private String name;  // 이름
    @NotNull
    private String registrationNumber;  // 등록번호
    @NotNull
    private String location;  // 위치
    @NotNull
    private String type;  // 유형
    @NotNull
    private String sector;  // 업종
    @NotNull
    private String ceo;  // CEO
    @NotNull
    private String telNumber;  // 전화번호
    private String faxNumber;  // 팩스번호
    private String email;  // 이메일
    @NotNull
    private String contacter;  // 담당자
    @NotNull
    private String contacterNumber;  // 담당자 연락처
    @NotNull
    private String contacterEmail;  // 담당자 이메일
    @NotNull
    private String id;  // ID
    @NotNull
    private String pw;  // 비밀번호
    @NotNull
    private String password;  // 비밀번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partScode")
    @NotNull
    private PartS partS;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @NotNull
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
