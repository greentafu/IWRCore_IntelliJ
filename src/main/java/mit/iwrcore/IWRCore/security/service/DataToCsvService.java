package mit.iwrcore.IWRCore.security.service;

import org.springframework.stereotype.Service;

@Service
public interface DataToCsvService {
    void exportDataToCSV(String filePath);
}
