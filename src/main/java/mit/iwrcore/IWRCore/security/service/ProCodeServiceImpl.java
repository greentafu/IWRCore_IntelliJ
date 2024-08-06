package mit.iwrcore.IWRCore.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.entity.ProM;
import mit.iwrcore.IWRCore.entity.ProS;
import mit.iwrcore.IWRCore.repository.ProLCodeRepository;
import mit.iwrcore.IWRCore.repository.ProMCodeRepository;
import mit.iwrcore.IWRCore.repository.ProSCodeRepository;
import mit.iwrcore.IWRCore.security.dto.ProLDTO;
import mit.iwrcore.IWRCore.security.dto.ProMDTO;
import mit.iwrcore.IWRCore.security.dto.ProSDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProCodeServiceImpl implements ProCodeService{

    private final ProLCodeRepository lCodeRepository;
    private final ProMCodeRepository mCodeRepository;
    private final ProSCodeRepository sCodeRepository;

    // 회사 분류 삽입
    @Override
    public void insertProL(ProLDTO dto) {
        log.info("제품 대분류 삽입");
        ProL proL=proLdtoToEntity(dto);
        lCodeRepository.save(proL);
    }
    @Override
    public void insertProM(ProMDTO dto) {
        log.info("제품 중분류 삽입");
        ProM proM=proMdtoToEntity(dto);
        mCodeRepository.save(proM);
    }
    @Override
    public void insertProS(ProSDTO dto) {
        log.info("제품 소분류 삽입");
        ProS proS=proSdtoToEntity(dto);
        sCodeRepository.save(proS);
    }

    // 회사 분류 삭제
    @Override
    public void deleteProL(Long lcode) {
        lCodeRepository.deleteById(lcode);
    }
    @Override
    public void deleteProM(Long mcode) {
        mCodeRepository.deleteById(mcode);
    }
    @Override
    public void deleteProS(Long scode) {
        sCodeRepository.deleteById(scode);
    }



    // 회사 분류 가져오기
    @Override
    public ProLDTO findProL(Long lcode) {
        return proLTodto(lCodeRepository.getById(lcode));
    }
    @Override
    public ProMDTO findProM(Long mcode) {
        return proMTodto(mCodeRepository.getById(mcode));
    }
    @Override
    public ProSDTO findProS(Long scode) {
        return proSTodto(sCodeRepository.getById(scode));
    }

    // 회사 분류 리스트 가져오기
    @Override
    public List<ProLDTO> findListProL(ProMDTO proMDTO, ProSDTO proSDTO) {
        List<ProLDTO> list=new ArrayList<>();
        if(proSDTO!=null){
            list.add(proLTodto(proMDTO.getProL().getProL()));
            return list;
        }else if(proMDTO !=null){
            list.add(proLTodto(proMDTO.getProL()));
            return list;
        }
        lCodeRepository.findAll().stream().forEach(x->list.add(proLTodto(x)));
        return list;
    }
    @Override
    public List<ProMDTO> findListProM(ProLDTO proLDTO, ProSDTO proSDTO) {
        List<ProMDTO> list=new ArrayList<>();
        if(proSDTO!=null){
            list.add(proMTodto(proSDTO.getProM()));
            return list;
        }else if(proLDTO!=null){
            mCodeRepository.findAll().stream().filter(x->x.getProL().getProLcode()==proLDTO.getProLcode()).forEach(x->list.add(proMTodto(x)));
            return list;
        }
        mCodeRepository.findAll().stream().forEach(x->list.add(proMTodto(x)));
        return list;
    }
    @Override
    public List<ProSDTO> findListProS(ProLDTO proLDTO, ProMDTO proMDTO) {
        List<ProSDTO> list=new ArrayList<>();
        if(proMDTO!=null){
            sCodeRepository.findAll().stream().filter(x->x.getProM().getProMcode()==proMDTO.getProMcode()).forEach(x->list.add(proSTodto(x)));
            return list;
        }else if(proLDTO!=null){
            sCodeRepository.findAll().stream().filter(x->x.getProM().getProL().getProLcode()==proLDTO.getProLcode()).forEach(x->list.add(proSTodto(x)));
            return list;
        }
        sCodeRepository.findAll().stream().forEach(x->list.add(proSTodto(x)));
        return list;
    }

    @Override
    public ProS ProSdtoToEntity(ProSDTO sdto) {
        return null;
    }

    @Override
    public ProM ProMdtoToEntity(ProMDTO proM) {
        return null;
    }

    @Override
    public ProL ProLdtoToEntity(ProLDTO proL) {
        return null;
    }


}
