package mit.iwrcore.IWRCore.security.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

//이제 javax사용 안함.
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class CsvExportService {

    public void exportDataToCsv(HttpServletResponse response) throws IOException {
        String jdbcUrl = "jdbc:mariadb://localhost:3307/iwlcore";
        String username = "iwlcore";
        String password = "iwlcore";
        String query = "SELECT manu_lcode, lname FROM prol";

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"data_export1.csv\"");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             OutputStream out = response.getOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new java.io.OutputStreamWriter(out), CSVFormat.DEFAULT.withHeader("manu_lcode", "lname"))) {

            while (resultSet.next()) {
                String manuLcode = resultSet.getString("manu_lcode");
                String lname = resultSet.getString("lname");
                csvPrinter.printRecord(manuLcode, lname);
            }

            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error while exporting data to CSV", e);
        }
    }
}
