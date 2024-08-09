package mit.iwrcore.IWRCore.repository;

import mit.iwrcore.IWRCore.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  MemberRepository extends JpaRepository<Member, String> {
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.id =:id")
    Optional<Member> findByID(String id);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.mno =:mno or m.id=:id")
    Member findMember(Long mno, String id);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query(value = "select m from Member m", countQuery = "select count(m) from Member m where m.mno>:mno")
    List<Member> findMemberList(Pageable pageable);
}
