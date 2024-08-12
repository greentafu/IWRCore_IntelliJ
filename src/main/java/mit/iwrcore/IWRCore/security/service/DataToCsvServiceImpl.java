package mit.iwrcore.IWRCore.security.service;

import mit.iwrcore.IWRCore.entity.ProL;
import mit.iwrcore.IWRCore.repository.ProLCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class DataToCsvServiceImpl implements DataToCsvService{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DataToCsvServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void exportDataToCSV(String filePath) {
        String sql = String.format(
                "SELECT `manu_lcode`, `lname` INTO OUTFILE '%s' " +
                        "FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '\"' " +
                        "FROM prol",
                filePath.replace("'", "''")
                //controller에서 경로 지정해주고 여기서 %S로 string 받음
        );

        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            e.printStackTrace(); // 로깅 프레임워크를 사용하여 예외를 기록할 수 있습니다.
        }
    }
}
