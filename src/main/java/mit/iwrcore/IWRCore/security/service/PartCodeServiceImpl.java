package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.PartL;
import mit.iwrcore.IWRCore.entity.PartM;
import mit.iwrcore.IWRCore.entity.PartS;
import mit.iwrcore.IWRCore.repository.PartLCodeRepository;
import mit.iwrcore.IWRCore.repository.PartMCodeRepository;
import mit.iwrcore.IWRCore.repository.PartSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.PartLDTO;
import mit.iwrcore.IWRCore.security.dto.PartMDTO;
import mit.iwrcore.IWRCore.security.dto.PartSDTO;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PartCodeServiceImpl implements PartCodeService{

    private final PartLCodeRepository lCodeRepository;
    private final PartMCodeRepository mCodeRepository;
    private final PartSCodeRepository sCodeRepository;

    // 회사 분류 삽입
    @Override
    public void insertPartL(PartLDTO dto) {
        log.info("협력회사 대분류 삽입");
        PartL partL=partLdtoToEntity(dto);
        lCodeRepository.save(partL);
    }
    @Override
    public void insertPartM(PartMDTO dto) {
        log.info("협력회사 중분류 삽입");
        PartM partM=partMdtoToEntity(dto);
        mCodeRepository.save(partM);
    }
    @Override
    public void insertPartS(PartSDTO dto) {
        log.info("협력회사 소분류 삽입");
        PartS partS=partSdtoToEntity(dto);
        sCodeRepository.save(partS);
    }

    // 회사 분류 삭제
    @Override
    public void deletePartL(Long lcode) {
        lCodeRepository.deleteById(lcode);
    }
    @Override
    public void deletePartM(Long mcode) {
        mCodeRepository.deleteById(mcode);
    }
    @Override
    public void deletePartS(Long scode) {
        sCodeRepository.deleteById(scode);
    }



    // 회사 분류 가져오기
    @Override
    public PartLDTO findPartL(Long lcode) {
        return partLTodto(lCodeRepository.getById(lcode));
    }
    @Override
    public PartMDTO findPartM(Long mcode) {
        return partMTOdto(mCodeRepository.getById(mcode));
    }
    @Override
    public PartSDTO findPartS(Long scode) {
        return partSTodto(sCodeRepository.getById(scode));
    }

    // 회사 분류 리스트 가져오기
    @Override
    public List<PartLDTO> findListPartL(PartMDTO partMDTO, PartSDTO partSDTO) {
        List<PartLDTO> list=new ArrayList<>();
        if(partSDTO!=null){
            list.add(partLTodto(partSDTO.getPartM().getPartL()));
            return list;
        }else if(partMDTO!=null){
            list.add(partLTodto(partMDTO.getPartL()));
            return list;
        }
        lCodeRepository.findAll().stream().forEach(x->list.add(partLTodto(x)));
        return list;
    }
    @Override
    public List<PartMDTO> findListPartM(PartLDTO partLDTO, PartSDTO partSDTO) {
        List<PartMDTO> list=new ArrayList<>();
        if(partSDTO!=null){
            list.add(partMTOdto(partSDTO.getPartM()));
            return list;
        }else if(partLDTO!=null){
            mCodeRepository.findAll().stream().filter(x->x.getPartL().getPartLcode()==partLDTO.getPartLcode()).forEach(x->list.add(partMTOdto(x)));
            return list;
        }
        mCodeRepository.findAll().stream().forEach(x->list.add(partMTOdto(x)));
        return list;
    }
    @Override
    public List<PartSDTO> findListPartS(PartLDTO partLDTO, PartMDTO partMDTO) {
        List<PartSDTO> list=new ArrayList<>();
        if(partMDTO!=null){
            sCodeRepository.findAll().stream().filter(x->x.getPartM().getPartMcode()==partMDTO.getPartMcode()).forEach(x->list.add(partSTodto(x)));
            return list;
        }else if(partLDTO!=null){
            sCodeRepository.findAll().stream().filter(x->x.getPartM().getPartL().getPartLcode()==partLDTO.getPartLcode()).forEach(x->list.add(partSTodto(x)));
            return list;
        }
        sCodeRepository.findAll().stream().forEach(x->list.add(partSTodto(x)));
        return list;
    }


}
