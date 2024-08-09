package mit.iwrcore.IWRCore.service;

import jakarta.transaction.Transactional;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;


@SpringBootTest
public class PageTests {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Commit
    public void test(){
//        Sort sort=Sort.by("mno").descending();
//        Pageable pageable= PageRequest.of(0, 10, sort);
//        Page<Member> list=memberRepository.findAll(pageable);
//        list.forEach(System.out::println);
//        System.out.println("##### 총 페이지수: "+list.getTotalPages());
//        System.out.println("##### 전체 갯수: "+list.getTotalElements());
//        System.out.println("##### 현재 페이지번호(0부터 시작): "+list.getNumber());
//        System.out.println("##### 페이지당 데이터 수: "+list.getSize());
//        System.out.println("##### 다음페이지 존재여부: "+list.hasNext());
//        System.out.println("##### 시작페이지 존재여부: "+list.hasPrevious());
    }


}
