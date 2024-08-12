package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import mit.iwrcore.IWRCore.entity.Gumsu;
import mit.iwrcore.IWRCore.repository.GumsuReposetory;
import mit.iwrcore.IWRCore.security.dto.GumsuDTO;
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

    @Override
    public Gumsu convertToEntity(GumsuDTO dto) {
        return Gumsu.builder()
                .gumsuNo(dto.getGumsuNo())
                .make(dto.getMake())
                .who(dto.getWho())
                .gumsuDate(dto.getGumsuDate())
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
                .gumsuDate(entity.getGumsuDate())
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
    public Optional<GumsuDTO> getGumsuById(Long id) {
        return gumsuReposetory.findById(id).map(this::convertToDTO);
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
}