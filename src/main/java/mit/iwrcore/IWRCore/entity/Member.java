package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity{
    @Id
    private Long mno;
    private String name;
    private String department;
    private String phonenumber;
    private String id;
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet=new HashSet<>();

    public void addMemberRole(MemberRole memberRole){
        roleSet.clear();
        roleSet.add(memberRole);
    }
}
