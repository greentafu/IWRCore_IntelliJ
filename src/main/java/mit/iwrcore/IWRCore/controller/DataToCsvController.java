package mit.iwrcore.IWRCore.controller;

import mit.iwrcore.IWRCore.security.service.DataToCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prol")
public class DataToCsvController {

    private final DataToCsvService dataToCsvService;

    @Autowired
    public DataToCsvController(DataToCsvService dataToCsvService) {
        this.dataToCsvService = dataToCsvService;
    }

    @GetMapping("/export")
    public String exportDataToCSV(@RequestParam String filePath) {
        dataToCsvService.exportDataToCSV(filePath);
        return "Data exported successfully!";
    }
}

