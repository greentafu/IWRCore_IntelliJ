package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.dto.MaterCodeListDTO;
import mit.iwrcore.IWRCore.entity.MaterL;
import mit.iwrcore.IWRCore.entity.MaterM;
import mit.iwrcore.IWRCore.entity.MaterS;
import mit.iwrcore.IWRCore.repository.Mater.MaterLRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterMRepository;
import mit.iwrcore.IWRCore.repository.Mater.MaterSRepository;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterLDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterMDTO;
import mit.iwrcore.IWRCore.security.dto.MaterDTO.MaterSDTO;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Log4j2
@Service
@RequiredArgsConstructor
public class MaterServiceImpl implements MaterService{
    private final MaterLRepository materLRepository;
    private final MaterMRepository materMRepository;
    private final MaterSRepository materSRepository;

    @Override
    public void insertML(MaterLDTO dto){
        log.info("자재 대분류 삽입");
        MaterL materL=materLdtoToEntity(dto);
        materLRepository.save(materL);
    }
    @Override
    public void insertMM(MaterMDTO dto){
        log.info("자재 중분류 삽입");
        MaterM materM=materMdtoToEntity(dto);
        materMRepository.save(materM);
    }
    @Override
    public void insertMS(MaterSDTO dto){
        log.info("자재 소분류 삽입");
        MaterS materS=materSdtoToEntity(dto);
        materSRepository.save(materS);
    }
    @Override
    public void deleteMaterL(Long lcode){materLRepository.deleteById(lcode);}
    @Override
    public void deleteMaterM(Long mcode){materMRepository.deleteById(mcode);}
    @Override
    public void deleteMaterS(Long scode){materSRepository.deleteById(scode);}

    @Override
    public MaterLDTO findMaterL(Long lcode){return materLTodto(materLRepository.getById(lcode));}
    @Override
    public MaterMDTO findMaterM(Long mcode){return materMTodto(materMRepository.getById(mcode));}
    @Override
    public MaterSDTO findMaterS(Long scode){return materSTodto(materSRepository.getById(scode));}

    @Override
    public List<MaterLDTO> findListMaterL() {
        List<MaterLDTO> list=new ArrayList<>();
        materLRepository.findAll().stream().forEach(x->list.add(materLTodto(x)));
        return list;
    }
    @Override
    public List<MaterMDTO> findListMaterM(MaterLDTO materLDTO, MaterMDTO materMDTO, MaterSDTO materSDTO) {
        List<MaterMDTO> list=new ArrayList<>();
        if(materSDTO!=null){
            Long temp=materSDTO.getMaterMDTO().getMaterLDTO().getMaterLcode();
            materMRepository.findAll().stream().filter(x->x.getMaterL().getMaterLcode()==temp).forEach(x->list.add(materMTodto(x)));
            return list;
        }else if(materMDTO!=null){
            Long temp=materMDTO.getMaterLDTO().getMaterLcode();
            materMRepository.findAll().stream().filter(x->x.getMaterL().getMaterLcode()==temp).forEach(x->list.add(materMTodto(x)));
            return list;
        }else if(materLDTO!=null){
            Long temp=materLDTO.getMaterLcode();
            materMRepository.findAll().stream().filter(x->x.getMaterL().getMaterLcode()==temp).forEach(x->list.add(materMTodto(x)));
            return list;
        }
        materMRepository.findAll().stream().forEach(x->list.add(materMTodto(x)));
        return list;
    }
    @Override
    public List<MaterSDTO> findListMaterS(MaterLDTO materLDTO, MaterMDTO materMDTO, MaterSDTO materSDTO) {
        List<MaterSDTO> list=new ArrayList<>();
        if(materSDTO!=null){
            Long temp=materSDTO.getMaterMDTO().getMaterMcode();
            materSRepository.findAll().stream().filter(x->x.getMaterM().getMaterMcode()==temp).forEach(x->list.add(materSTodto(x)));
            return list;
        }else if(materMDTO!=null){
            Long temp=materMDTO.getMaterMcode();
            materSRepository.findAll().stream().filter(x->x.getMaterM().getMaterMcode()==temp).forEach(x->list.add(materSTodto(x)));
            return list;
        }else if(materLDTO!=null){
            Long temp=materLDTO.getMaterLcode();
            materSRepository.findAll().stream().filter(x->x.getMaterM().getMaterL().getMaterLcode()==temp).forEach(x->list.add(materSTodto(x)));
            return list;
        }
        materSRepository.findAll().stream().forEach(x->list.add(materSTodto(x)));
        return list;
    }
    @Override
    public MaterCodeListDTO findListMaterAll(MaterLDTO materLDTO, MaterMDTO materMDTO, MaterSDTO materSDTO) {
        MaterCodeListDTO list=MaterCodeListDTO.builder()
                .materLDTOs(findListMaterL())
                .materMDTOs(findListMaterM(materLDTO, materMDTO, materSDTO))
                .materSDTOs(findListMaterS(materLDTO, materMDTO, materSDTO))
                .build();
        return list;
    }


}