package mit.iwrcore.IWRCore.controller;

import mit.iwrcore.IWRCore.security.service.CsvExportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CsvExportController {

    //final 없애고 수정가능하게함
    private CsvExportService csvExportService;

    public CsvExportController(CsvExportService csvExportService) {
        this.csvExportService = csvExportService;
    }

    @PostMapping("/export")
    public void exportToCsv(HttpServletResponse response) {
        try {
            csvExportService.exportDataToCsv(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
