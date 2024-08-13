package mit.iwrcore.IWRCore.security.service;

import jakarta.transaction.Transactional;

import mit.iwrcore.IWRCore.entity.JodalChasu;
import mit.iwrcore.IWRCore.repository.JodalChasuRepository;

import mit.iwrcore.IWRCore.security.dto.JodalChasuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional

public class JodalChasuServiceImpl implements JodalChasuService {
    private final JodalChasuRepository jodalChasuRepository;
    private final MemberService memberService;
    private final JodalPlanService jodalPlanService;

    @Autowired
    public JodalChasuServiceImpl(JodalChasuRepository jodalChasuRepository,
                                 MemberService memberService,
                                 JodalPlanService jodalPlanService) {
        this.jodalChasuRepository = jodalChasuRepository;
        this.memberService = memberService;
        this.jodalPlanService = jodalPlanService;
    }

    @Override
    public JodalChasuDTO convertToDTO(JodalChasu entity) {
        return JodalChasuDTO.builder()
                .joNum(entity.getJoNum())
                .joDate(entity.getJoDate())
                .memberDTO(memberService.memberTodto(entity.getWriter())) // Assuming memberService has this method
                .jodalPlanDTO(jodalPlanService.entityToDTO(entity.getJodalPlan())) // Assuming jodalPlanService has this method
                .build();
    }

    @Override
    public JodalChasu convertToEntity(JodalChasuDTO dto) {
        return JodalChasu.builder()
                .joNum(dto.getJoNum())
                .joDate(dto.getJoDate())
                .writer(memberService.memberdtoToEntity(dto.getMemberDTO())) // Assuming memberService has this method
                .jodalPlan(jodalPlanService.dtoToEntity(dto.getJodalPlanDTO())) // Assuming jodalPlanService has this method
                .build();
    }

    @Override
    public JodalChasuDTO createJodalChasu(JodalChasuDTO dto) {
        JodalChasu entity = convertToEntity(dto);
        JodalChasu savedEntity = jodalChasuRepository.save(entity);
        return convertToDTO(savedEntity);
    }

    @Override
    public Optional<JodalChasuDTO> getJodalChasuById(Long id) {
        return jodalChasuRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public JodalChasuDTO updateJodalChasu(Long id, JodalChasuDTO dto) {
        if (!jodalChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 JodalChasuDTO를 찾을 수 없습니다.");
        }
        JodalChasu entity = convertToEntity(dto);
        entity.setJoNum(id); // 수정할 때 ID를 설정합니다.
        JodalChasu updatedEntity = jodalChasuRepository.save(entity);
        return convertToDTO(updatedEntity);
    }

    @Override
    public void deleteJodalChasu(Long id) {
        if (!jodalChasuRepository.existsById(id)) {
            throw new RuntimeException("ID가 " + id + "인 JodalChasuDTO를 찾을 수 없습니다.");
        }
        jodalChasuRepository.deleteById(id);
    }

    @Override
    public List<JodalChasuDTO> getAllJodalChasus() {
        return jodalChasuRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}