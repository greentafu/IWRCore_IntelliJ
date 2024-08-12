package mit.iwrcore.IWRCore.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CsvExportServiceTests {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mariadb://localhost:3307/iwlcore";
        String username = "iwlcore";
        String password = "iwlcore";
        String query = "SELECT manu_lcode, lname FROM prol";
        String csvFilePath = "data_export1.csv";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             FileWriter out = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("manu_lcode", "lname"))) {

            while (resultSet.next()) {
                String manuLcode = resultSet.getString("manu_lcode");
                String lname = resultSet.getString("lname");
                csvPrinter.printRecord(manuLcode, lname);
            }

            csvPrinter.flush();
            System.out.println("Data exported to CSV successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
