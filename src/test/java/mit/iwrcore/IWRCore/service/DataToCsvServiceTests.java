package mit.iwrcore.IWRCore.service;

import mit.iwrcore.IWRCore.security.service.DataToCsvService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;






@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DataToCsvServiceTests {

    @Autowired
    private DataToCsvService dataToCsvService;

    @Test
    @Commit
    public void exportData() {
        String filePath = "C:/Program Files/MariaDB 10.11/data/filename10.csv"; // MariaDB가 접근할 수 있는 경로
        dataToCsvService.exportDataToCSV(filePath);

    }
}

