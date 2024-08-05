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
import org.springframework.stereotype.Service;

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
    public PartL findPartL(Long lcode) {
        return lCodeRepository.getReferenceById(lcode);
    }
    @Override
    public PartM findPartM(Long mcode) {
        return mCodeRepository.getReferenceById(mcode);
    }
    @Override
    public PartS findPartS(Long scode) {
        return sCodeRepository.getReferenceById(scode);
    }
    // 회사 분류 리스트 가져오기
    @Override
    public List<PartL> findListPartL(PartM partM, PartS partS) {
        return List.of();
    }

    @Override
    public List<PartM> findListPartM(PartL partL, PartS partS) {
        return List.of();
    }

    @Override
    public List<PartS> findListPartS(PartL partL, PartS partS) {
        return List.of();
    }


}
