package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.Material;
import mit.iwrcore.IWRCore.repository.BoxRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.repository.MaterialRepository;
import mit.iwrcore.IWRCore.security.dto.BoxDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import mit.iwrcore.IWRCore.security.dto.MaterialDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public void insertJa(MaterialDTO dto){
        log.info("자재 삽입");
        Material material=materEntity(dto);
        materialRepository.save(material);
    }
    @Override
    public void deleteJa(Long materCode){materialRepository.deleteById(materCode);}

    @Override
    public MaterialDTO findM(Long matercode){return materTodto(materialRepository.getById(matercode));}

    @Override
    public List<MaterialDTO> BfindList(BoxDTO boxDTO) {
        List<MaterialDTO> list = new ArrayList<>();
        if (boxDTO != null) {
            materialRepository.findAll().stream().filter(x->x.getMaterCode()==boxDTO.getBoxcode()).forEach(x->list.add(materTodto(x)));
        }
        return list;
    }
    @Override
    public List<MaterialDTO> MfindList(MaterSDTO materSDTO) {
        List<MaterialDTO> list = new ArrayList<>();
        if (materSDTO != null) {
            materialRepository.findAll().stream().filter(x->x.getMaterCode()==materSDTO.getMaterScode()).forEach(x->list.add(materTodto(x)));
        }
        return list;
    }
}




