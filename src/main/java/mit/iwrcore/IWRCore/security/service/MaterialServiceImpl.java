package mit.iwrcore.IWRCore.security.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import mit.iwrcore.IWRCore.security.dto.MemberDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.PartDTO.PartSDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MemberService memberService;
    private final MaterService materService;
    private final BoxService boxService;

    @Override
    public void insertj(MaterialDTO dto) {
        log.info("Inserting material");
        Material material =materdtoToEntity(dto);
        materialRepository.save(material);
    }

    @Override
    public MaterialDTO findM(Long materCode) {
        log.info("Finding material with code: {}", materCode);
        return materialRepository.findById(materCode)
                .map(this::materTodto)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }

    @Override
    public PageResultDTO<MaterialDTO, Material> findMaterialAll(PageRequestDTO requestDTO){
        Pageable pageable=requestDTO.getPageable(Sort.by("materCode").descending());

        BooleanBuilder booleanBuilder=getSearch(requestDTO);

        Page<Material> entityPage=materialRepository.findAll(booleanBuilder, pageable);
        Function<Material, MaterialDTO> fn=(entity->materTodto(entity));

        PageResultDTO pageResultDTO=new PageResultDTO<>(entityPage, fn);
        pageResultDTO.setMaterL(requestDTO.getMaterL());
        pageResultDTO.setMaterM(requestDTO.getMaterM());
        pageResultDTO.setMaterS(requestDTO.getMaterS());
        pageResultDTO.setMaterialSearch(requestDTO.getMaterialSearch());

        return pageResultDTO;
    }

    @Override
    public List<Material> findMaterialPart(Long boxcode, Long materscode){
        return materialRepository.materialListPart(boxcode, materscode);
    }
    @Override
    public void deleteJa(Long materCode) {
        log.info("Deleting material with code: {}", materCode);
        materialRepository.deleteById(materCode);
    }

    // dto를 entity로
    @Override
    public Material materdtoToEntity(MaterialDTO dto){
        Material entity=Material.builder()
                .materCode(dto.getMaterCode())
                .name(dto.getName())
                .unit(dto.getUnit())
                .standard(dto.getStandard())
                .color(dto.getColor())
                .file(dto.getFile())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO()))
                .materS(materService.materSdtoToEntity(dto.getMaterSDTO()))
                .box(boxService.boxdtoToEntity(dto.getBoxDTO()))
                .build();
        return entity;
    }
    // entity를 dto로
    @Override
    public MaterialDTO materTodto(Material entity){
        MaterialDTO dto=MaterialDTO.builder()
                .materCode(entity.getMaterCode())
                .name(entity.getName())
                .unit(entity.getUnit())
                .standard(entity.getStandard())
                .color(entity.getColor())
                .file(entity.getFile())
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .materSDTO(materService.materSTodto(entity.getMaterS()))
                .boxDTO(boxService.boxTodto(entity.getBox()))
                .build();
        return dto;
    }

    // 검색조건
    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        Long materL=requestDTO.getMaterL();
        Long materM=requestDTO.getMaterM();
        Long materS= requestDTO.getMaterS();
        String materSearch= requestDTO.getMaterialSearch();

        BooleanBuilder booleanBuilder=new BooleanBuilder();
        QMaterial qMaterial=QMaterial.material;
        BooleanExpression expression=qMaterial.materCode.gt(0L); // materCode>0

        booleanBuilder.and(expression);

        if(materL==null && materM==null && materS==null && materSearch==null){
            return booleanBuilder;
        }

        BooleanBuilder conditionBuilder1=new BooleanBuilder();
        if(materS!=null){
            MaterS ms=materService.materSdtoToEntity(materService.findMaterS(materS));
            conditionBuilder1.and(qMaterial.materS.eq(ms));
        }else if(materM!=null){
            List<MaterSDTO> sdtoList=materService.findListMaterS(null, materService.findMaterM(materM), null);
            List<MaterS> sList=sdtoList.stream().map(x->materService.materSdtoToEntity(x)).collect(Collectors.toList());
            conditionBuilder1.and(qMaterial.materS.in(sList));
        }else if(materL!=null){
            List<MaterSDTO> sdtoList=materService.findListMaterS(materService.findMaterL(materL), null, null);
            List<MaterS> sList=sdtoList.stream().map(x->materService.materSdtoToEntity(x)).collect(Collectors.toList());
            conditionBuilder1.and(qMaterial.materS.in(sList));
        }

        BooleanBuilder conditionBuilder2=new BooleanBuilder();
        if(materSearch!=null){
            conditionBuilder2.and(qMaterial.name.contains(materSearch));
        }

        booleanBuilder.and(conditionBuilder1).and(conditionBuilder2);
        return booleanBuilder;
    }

}



