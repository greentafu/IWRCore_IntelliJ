package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Box;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
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
    private final MaterSRepository materSRepository;
    private final MaterialRepository materialRepository;
    private final BoxRepository boxRepository;


    @Override
    public void insertj(MaterialDTO dto) {
        log.info("Inserting material");
        Material material = materEntity(dto);
        materialRepository.save(material);
    }
    @Override
    public void insertbox(BoxDTO dto) {
        log.info("Inserting box");
        Box box = boxEntity(dto);
        boxRepository.save(box);
    }
    @Override
    public void insertsmater(MaterSDTO dto) {
        log.info("Inserting materS");
        MaterS materS = matersEntity(dto);
        materSRepository.save(materS);
    }



    @Override
    public void deleteJa(Long materCode) {
        log.info("Deleting material with code: {}", materCode);
        materialRepository.deleteById(materCode);
    }

    @Override
    public MaterialDTO findM(Long materCode) {
        log.info("Finding material with code: {}", materCode);
        return materialRepository.findById(materCode)
                .map(this::materTodto)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }

    @Override
    public List<MaterialDTO> BfindList(BoxDTO boxDTO) {
        log.info("Finding materials for box: {}", boxDTO);
        if (boxDTO != null) {
            return materialRepository.findAll().stream()
                    .filter(material -> material.getBox() != null &&
                            material.getBox().getBoxCode().equals(boxDTO.getBoxcode()))
                    .map(this::materTodto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<MaterialDTO> MfindList(MaterSDTO materSDTO) {
        log.info("Finding materials for materS: {}", materSDTO);
        if (materSDTO != null) {
            return materialRepository.findAll().stream()
                    .filter(material -> material.getMaterS() != null &&
                            material.getMaterS().getMaterScode().equals(materSDTO.getMaterScode()))
                    .map(this::materTodto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}



