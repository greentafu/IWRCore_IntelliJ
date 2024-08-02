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
    private Long pno;
    private String name;
    private String registrationNumber;
    private String location;
    private String type;
    private String sector;
    private String ceo;
    private String telNumber;
    private String faxNumber;
    private String email;
    private String contacter;
    private String contacterNumber;
    private String contacterEmail;
    private String id;
    private String pw;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet=new HashSet<>();

    @PostPersist
    public void generateId(){
        if(this.id==null){
            String temp="partner"+pno+"_"+registrationNumber.substring(7);
            this.id=temp;
        }
    }

    public void setPartnerRole(MemberRole partnerRole){
        roleSet.clear();
        roleSet.add(partnerRole);
    }
}
