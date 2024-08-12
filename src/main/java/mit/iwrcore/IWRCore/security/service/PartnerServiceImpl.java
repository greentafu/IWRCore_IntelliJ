package mit.iwrcore.IWRCore.security.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.PartnerRepository;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;
import mit.iwrcore.IWRCore.security.dto.PartnerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService{

    private final PartnerRepository partnerRepository;

    private final PartCodeService partCodeService;

    // 회사찾기
    @Override
    public Partner findPartnerEntity(Long pno, String id, String reg_number) {
        return partnerRepository.findPartner(pno, id, reg_number);
    }
    @Override
    public PartnerDTO findPartnerDto(Long pno, String id, String reg_number) {
        return partnerTodto(partnerRepository.findPartner(pno, id, reg_number));
    }

    // 회사 목록 찾기
    @Override
    public PageResultDTO<PartnerDTO, Partner> findPartnerList(PageRequestDTO pageRequestDTO) {
        Pageable pageable=pageRequestDTO.getPageable(Sort.by("pno").descending());

        BooleanBuilder booleanBuilder=getSearch(pageRequestDTO);

        Page<Partner> partnerList=partnerRepository.findAll(booleanBuilder, pageable);
        Function<Partner, PartnerDTO> fn=(entity->partnerTodto(entity));

        PageResultDTO pageResultDTO=new PageResultDTO<>(partnerList, fn);
        pageResultDTO.setPartL(pageRequestDTO.getPartL());
        pageResultDTO.setPartM(pageRequestDTO.getPartM());
        pageResultDTO.setPartS(pageRequestDTO.getPartS());
        pageResultDTO.setPartnerSearch(pageRequestDTO.getPartnerSearch());

        return pageResultDTO;
    }

    // 새로운 회사 계정 생성시 사업자등록번호 중복은 1, 아이디 중복은 2, 성공시에는 0
    @Override
    public Integer insertPartner(PartnerDTO dto) {
        Partner partner_id=findPartnerEntity(null, dto.getId(), null);
        Partner partner_reg=findPartnerEntity(null, null, dto.getRegistrationNumber());
        if(partner_reg!=null){
            return 1;
        }else if(partner_id!=null){
            return 2;
        }
        partnerRepository.save(partnerDtoToEntity(dto));
        return 0;
    }

    @Override
    public void deletePartner(Long pno) {
        Partner partner=findPartnerEntity(pno, null, null);
        partnerRepository.delete(partner);
    }


    // entity를 dto로
    @Override
    public PartnerDTO partnerTodto(Partner entity){
        PartnerDTO dto=PartnerDTO.builder()
                .pno(entity.getPno())
                .name(entity.getName())
                .registrationNumber(entity.getRegistrationNumber())
                .location(entity.getLocation())
                .type(entity.getType())
                .sector(entity.getSector())
                .ceo(entity.getCeo())
                .telNumber(entity.getTelNumber())
                .email(entity.getEmail())
                .faxNumber(entity.getFaxNumber())
                .contacter(entity.getContacter())
                .contacterNumber(entity.getContacterNumber())
                .contacterEmail(entity.getContacterEmail())
                .roleSet(entity.getRoleSet())
                .id(entity.getId())
                .pw(entity.getPw())
                .password(entity.getPassword())
                .partSDTO(partCodeService.partSTodto(entity.getPartS()))
                .build();
        return dto;
    }
    // dto를 entity로
    @Override
    public Partner partnerDtoToEntity(PartnerDTO dto){
        Partner entity=Partner.builder()
                .pno(dto.getPno())
                .name(dto.getName())
                .registrationNumber(dto.getRegistrationNumber())
                .location(dto.getLocation())
                .type(dto.getType())
                .sector(dto.getSector())
                .ceo(dto.getCeo())
                .telNumber(dto.getTelNumber())
                .email(dto.getEmail())
                .faxNumber(dto.getFaxNumber())
                .contacter(dto.getContacter())
                .contacterNumber(dto.getContacterNumber())
                .contacterEmail(dto.getContacterEmail())
                .roleSet(dto.getRoleSet())
                .id(dto.getId())
                .pw(dto.getPw())
                .password(dto.getPassword())
                .partS(partCodeService.partSdtoToEntity(dto.getPartSDTO()))
                .build();
        return entity;
    }

    // 검색조건
    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        Long partL=requestDTO.getPartL();
        Long partM=requestDTO.getPartM();
        Long partS= requestDTO.getPartS();
        String partnerSearch= requestDTO.getPartnerSearch();

        BooleanBuilder booleanBuilder=new BooleanBuilder();
        QPartner qPartner=QPartner.partner;
        BooleanExpression expression=qPartner.pno.gt(0L); // pno>0

        booleanBuilder.and(expression);

        if(partL==null && partM==null && partS==null && partnerSearch==null){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder1=new BooleanBuilder();
        if(partS!=null){
            PartS ps=partCodeService.partSdtoToEntity(partCodeService.findPartS(partS));
            conditionBuilder1.and(qPartner.partS.eq(ps));
        }else if(partM!=null){
            List<PartSDTO> sdtoList=partCodeService.findListPartS(null, partCodeService.findPartM(partM),null);
            List<PartS> sList=sdtoList.stream().map(x->partCodeService.partSdtoToEntity(x)).collect(Collectors.toList());
            conditionBuilder1.and(qPartner.partS.in(sList));
        }else if(partL!=null){
            List<PartSDTO> sdtoList=partCodeService.findListPartS(partCodeService.findPartL(partL), null,null);
            List<PartS> sList=sdtoList.stream().map(x->partCodeService.partSdtoToEntity(x)).collect(Collectors.toList());
            conditionBuilder1.and(qPartner.partS.in(sList));
        }

        BooleanBuilder conditionBuilder2=new BooleanBuilder();
        if(partnerSearch!=null){
            conditionBuilder2
                    .or(qPartner.name.contains(partnerSearch))
                    .or(qPartner.registrationNumber.contains(partnerSearch))
                    .or(qPartner.type.contains(partnerSearch))
                    .or(qPartner.sector.contains(partnerSearch))
                    .or(qPartner.telNumber.contains(partnerSearch));
        }

        booleanBuilder.and(conditionBuilder1).and(conditionBuilder2);
        return booleanBuilder;
    }
}
