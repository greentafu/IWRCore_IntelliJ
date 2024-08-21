package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.*;
import mit.iwrcore.IWRCore.repository.GumsuReposetory;
import mit.iwrcore.IWRCore.security.dto.BaljuDTO;
import mit.iwrcore.IWRCore.security.dto.ContractDTO;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageRequestDTO;
import mit.iwrcore.IWRCore.security.dto.PageDTO.PageResultDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuGumsuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.BaljuJodalChasuDTO;
import mit.iwrcore.IWRCore.security.dto.multiDTO.ContractBaljuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GumsuServiceImpl implements GumsuService{
    private final GumsuReposetory gumsuReposetory;
    private final BaljuService baljuService;
    private final MemberService memberService;
    private final JodalChasuService jodalChasuService;

    @Override
    public Gumsu convertToEntity(GumsuDTO dto) {
        return Gumsu.builder()
                .gumsuNo(dto.getGumsuNo())
                .make(dto.getMake())
                .who(dto.getWho())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .balju(baljuService.convertToEntity(dto.getBaljuDTO()))   // BaljuDTO를 Balju로 변환
                .build();
    }

    @Override
    public GumsuDTO convertToDTO(Gumsu entity) {
        return GumsuDTO.builder()
                .gumsuNo(entity.getGumsuNo())
                .make(entity.getMake())
                .who(entity.getWho())
                .baljuDTO(baljuService.convertToDTO(entity.getBalju())) // Balju를 BaljuDTO로 변환
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Member를 MemberDTO로 변환
                .build();
    }

    @Override
    public GumsuDTO createGumsu(GumsuDTO gumsuDTO) {
        Gumsu gumsu = convertToEntity(gumsuDTO);
        Gumsu savedGumsu = gumsuReposetory.save(gumsu);
        return convertToDTO(savedGumsu);
    }

    @Override
    public GumsuDTO getGumsuById(Long id) {
        List<Object[]> list=gumsuReposetory.getGumsuFromBalju(id);
        Gumsu gumsu=(Gumsu) list.get(0)[0];
        return convertToDTO(gumsu);
    }

    @Override
    public GumsuDTO updateGumsu(Long id, GumsuDTO gumsuDTO) {
        if (!gumsuReposetory.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuDTO를 찾을 수 없습니다.");
        }
        Gumsu gumsu = convertToEntity(gumsuDTO);
        gumsu.setGumsuNo(id); // 수정할 때 ID를 설정합니다.
        Gumsu updatedGumsu = gumsuReposetory.save(gumsu);
        return convertToDTO(updatedGumsu);
    }

    @Override
    public void deleteGumsu(Long id) {
        if (!gumsuReposetory.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuDTO를 찾을 수 없습니다.");
        }
        gumsuReposetory.deleteById(id);
    }

    @Override
    public List<GumsuDTO> getAllGumsus() {
        return gumsuReposetory.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PageResultDTO<BaljuGumsuDTO, Object[]> couldGumsu(PageRequestDTO requestDTO) {
        Pageable pageable=requestDTO.getPageable(Sort.by("baljuNo").descending());
        Page<Object[]> entityPage=gumsuReposetory.couldGumsu(pageable);
        return new PageResultDTO<>(entityPage, this::BaljuGumsuToDTO);
    }
    private BaljuGumsuDTO BaljuGumsuToDTO(Object[] objects){
        Balju balju=(Balju) objects[0];
        Gumsu gumsu=(Gumsu) objects[1];
        BaljuDTO baljuDTO=(balju!=null)? baljuService.convertToDTO(balju):null;
        GumsuDTO gumsuDTO=(gumsu!=null)? convertToDTO(gumsu):null;
        return new BaljuGumsuDTO(baljuDTO, gumsuDTO);
    }

    @Override
    public Long getQuantityMake(Long baljuNo){
        return gumsuReposetory.quantityMake(baljuNo);
    }

    @Override
    public List<Partner> getNonGumsuPartner(){return gumsuReposetory.getNonGumsuPartner();}

    @Override
    public List<BaljuJodalChasuDTO> getNoneGumsuBalju(Long pno){
        List<Object[]> list=gumsuReposetory.getNoneGumsu(pno);
        List<BaljuJodalChasuDTO> dtoList=list.stream().map(this::baljuJodalChasuToDTO).toList();
        return dtoList;
    }
    private BaljuJodalChasuDTO baljuJodalChasuToDTO(Object[] objects){
        Balju balju=(Balju) objects[0];
        JodalChasu jodalChasu=(JodalChasu) objects[1];
        BaljuDTO baljuDTO=(balju!=null)?baljuService.convertToDTO(balju):null;
        JodalChasuDTO jodalChasuDTO=(jodalChasu!=null)?jodalChasuService.convertToDTO(jodalChasu):null;
        return new BaljuJodalChasuDTO(baljuDTO, jodalChasuDTO);
    }

}