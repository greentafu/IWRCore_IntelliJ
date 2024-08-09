package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public List<Material> findMaterialAll(){
        return materialRepository.materialList();
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
    public MaterialDTO materTodto(Material entity){
        MaterialDTO dto=MaterialDTO.builder()
                .materCode(entity.getMaterCode())
                .name(entity.getName())
                .unit(entity.getUnit())
                .standard(entity.getStandard())
                .color(entity.getColor())
                .file(entity.getFile())
                .date(entity.getRegDate())
                .memberDTO(memberService.memberTodto(entity.getWriter()))
                .materSDTO(materService.materSTodto(entity.getMaterS()))
                .boxDTO(boxService.boxTodto(entity.getBox()))
                .build();
        return dto;
    }

//    @Override
//    public void insertsmater(MaterSDTO dto) {
//        log.info("Inserting materS");
//        MaterS materS = matersEntity(dto);
//        materSRepository.save(materS);
//    }

}



