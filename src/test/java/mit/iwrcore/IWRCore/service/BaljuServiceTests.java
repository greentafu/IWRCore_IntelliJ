package mit.iwrcore.IWRCore.service;


import mit.iwrcore.IWRCore.repository.BaljuRepository;
import mit.iwrcore.IWRCore.repository.ContractRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;

import mit.iwrcore.IWRCore.security.service.BaljuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaljuServiceTests {
    @Autowired
    private BaljuService baljuService;

    @Autowired
    private BaljuRepository baljuRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ContractRepository contractRepository;



}
