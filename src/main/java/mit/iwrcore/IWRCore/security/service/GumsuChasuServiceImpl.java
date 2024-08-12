package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.entity.GumsuChasu;
import mit.iwrcore.IWRCore.entity.Member;
import mit.iwrcore.IWRCore.repository.GumsuChasuRepository;
import mit.iwrcore.IWRCore.repository.GumsuReposetory;
import mit.iwrcore.IWRCore.repository.MemberRepository;
import mit.iwrcore.IWRCore.security.dto.GumsuChasuDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GumsuChasuServiceImpl implements GumsuChasuService{
    private final GumsuChasuRepository gumsuChasuRepository;
    private final MemberService memberService;
    private final GumsuService gumsuService;

    @Override
    public GumsuChasu convertToEntity(GumsuChasuDTO dto) {
        return GumsuChasu.builder()
                .gumsuNum(dto.getGumsuNum())
                .gumsu1(dto.getGumsu1())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // MemberDTO를 Member로 변환
                .gumsu(gumsuService.convertToEntity(dto.getGumsuDTO()))   // GumsuDTO를 Gumsu로 변환
                .build();
    }

    @Override
    public GumsuChasuDTO convertToDTO(GumsuChasu entity) {
        return GumsuChasuDTO.builder()
                .gumsuNum(entity.getGumsuNum())
                .gumsu1(entity.getGumsu1())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Member를 MemberDTO로 변환
                .gumsuDTO(gumsuService.convertToDTO(entity.getGumsu()))   // Gumsu를 GumsuDTO로 변환
                .build();
    }

    @Override
    public GumsuChasuDTO createGumsuChasu(GumsuChasuDTO gumsuChasuDTO) {
        GumsuChasu gumsuChasu = convertToEntity(gumsuChasuDTO);
        GumsuChasu savedGumsuChasu = gumsuChasuRepository.save(gumsuChasu);
        return convertToDTO(savedGumsuChasu);
    }

    @Override
    public Optional<GumsuChasuDTO> getGumsuChasuById(Long id) {
        return gumsuChasuRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public GumsuChasuDTO updateGumsuChasu(Long id, GumsuChasuDTO gumsuChasuDTO) {
        if (!gumsuChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuChasuDTO를 찾을 수 없습니다.");
        }
        GumsuChasu gumsuChasu = convertToEntity(gumsuChasuDTO);
        gumsuChasu.setGumsuNum(id); // 수정할 때 ID를 설정합니다.
        GumsuChasu updatedGumsuChasu = gumsuChasuRepository.save(gumsuChasu);
        return convertToDTO(updatedGumsuChasu);
    }

    @Override
    public void deleteGumsuChasu(Long id) {
        if (!gumsuChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 GumsuChasuDTO를 찾을 수 없습니다.");
        }
        gumsuChasuRepository.deleteById(id);
    }

    @Override
    public List<GumsuChasuDTO> getAllGumsuChasus() {
        return gumsuChasuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}