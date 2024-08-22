package mit.iwrcore.IWRCore.security.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 해당페이지 데이터 출력은 javascript로 정적데이터 출력해라
public class CsvExportService {

    public void exportDataToCsv(HttpServletResponse response) throws IOException {
        String jdbcUrl = "jdbc:mariadb://localhost:3307/iwlcore";
        String username = "iwlcore";
        String password = "iwlcore";
        String query =
                //전체 재고수량 컬럼도 들어가야함.
                "SELECT m.mater_code AS '자재 번호', " +
                        "       m.name AS '자재명', " +
                        "       m.mater_scode AS '카테고리', " +
                        "       m.box_code AS '창고위치', " +
                        "       CASE " +
                        "           WHEN baljuDone.balju_id IS NOT NULL AND gc.gumsu1 < NOW() THEN '발주완료' " +
                        "           WHEN gc.gumsu1 >= NOW() THEN '발주진행' " +
                        "       END AS '발주 상태' " +
                        "FROM " +
                        "    (SELECT g.gumsu_no, g.balju_id " +
                        "     FROM gumsu g " +
                        "     LEFT JOIN gumsu_chasu gc ON g.gumsu_no = gc.gumsu_id " +
                        "     WHERE gc.gumsu1 < NOW() " +
                        "    ) AS baljuDone " +
                        "    INNER JOIN balju b ON baljuDone.balju_id = b.balju_no " +
                        "    INNER JOIN contract c ON b.contract_id = c.con_no " +
                        "    INNER JOIN jodal_plan j ON c.jodal_plan_jo_no = j.jo_no " +
                        "    INNER JOIN material m ON j.mater_code = m.mater_code " +
                        "    LEFT JOIN gumsu_chasu gc ON baljuDone.gumsu_no = gc.gumsu_id ";
        //"WHERE m.mater_code = 1"; // 자재 코드가 1인 레코드만 선택

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String currentDateTime = LocalDateTime.now().format(formatter);

        String filename = currentDateTime + "+data_export.csv";

        response.setContentType("text/csv");
        //response.setHeader("Content-Disposition", "attachment; filename=\"data_export.csv\""); //@@@@@@@@@@@@현재날짜+data_export.csv로 출력되게 하기(중복을 피하기 위함)
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             OutputStream out = response.getOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new OutputStreamWriter(out),
                     CSVFormat.DEFAULT.withHeader("자재 번호", "자재명", "카테고리", "창고위치", "발주 상태"))) {

            while (resultSet.next()) {
                String materCode = resultSet.getString("자재 번호");
                String name = resultSet.getString("자재명");
                String materScode = resultSet.getString("카테고리");
                String boxCode = resultSet.getString("창고위치");
                String status = resultSet.getString("발주 상태");
                csvPrinter.printRecord(materCode, name, materScode, boxCode, status);
            }

            csvPrinter.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Error while exporting data to CSV", e);
        }
    }
}