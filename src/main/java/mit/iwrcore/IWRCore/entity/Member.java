package mit.iwrcore.IWRCore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Embeddable
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    @NotNull
    private String name;
    @NotNull
    private String department;
    @NotNull
    private String phonenumber;
    @NotNull
    private String id;
    @NotNull
    private String pw;
    @NotNull
    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    @NotNull
    private Set<MemberRole> roleSet=new HashSet<>();


    @PostPersist
    public void generateId(){
        if(this.id==null){
            String tempId=headId(department)+mno+"_"+phonenumber.substring(9);
            this.id=tempId;
        }
    }

    private String headId(String str){
        String head="";
        switch(str) {
            case "자재부서": head="mate"; break;
            case "개발부서": head="deve"; break;
            case "생산부서": head="prod";
        }
        return head;
    }

    public void changeMemberRole(MemberRole memberRole){
        roleSet.clear();
        roleSet.add(memberRole);
    }
}
