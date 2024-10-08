package mit.iwrcore.IWRCore.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CsvExportServiceTests {

    public static void main(String[] args) {
        // 데이터베이스 연결 정보
        String jdbcUrl = "jdbc:mariadb://localhost:3307/iwlcore";
        String username = "iwlcore";
        String password = "iwlcore";

        // 쿼리
        String query =  "SELECT m.mater_code AS '자재 번호', " +
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
                "    LEFT JOIN gumsu_chasu gc ON baljuDone.gumsu_no = gc.gumsu_id";

        // 현재 날짜와 시간을 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String currentDateTime = LocalDateTime.now().format(formatter);

        // 파일 이름 설정
        String csvFilePath = currentDateTime + "_data_export.csv";

        // CSV 파일 생성 및 데이터 쓰기
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             FileWriter fileWriter = new FileWriter(csvFilePath);
             CSVPrinter csvPrinter = new CSVPrinter(fileWriter,
                     CSVFormat.DEFAULT.withHeader("자재 번호", "자재명", "카테고리", "창고위치", "발주 상태"))) {

            // 데이터 조회 및 CSV 파일에 기록
            while (resultSet.next()) {
                String materCode = resultSet.getString("자재 번호");
                String name = resultSet.getString("자재명");
                String materScode = resultSet.getString("카테고리");
                String boxCode = resultSet.getString("창고위치");
                String status = resultSet.getString("발주 상태");
                csvPrinter.printRecord(materCode, name, materScode, boxCode, status);
            }

            // CSVPrinter 플러시
            csvPrinter.flush();
            System.out.println("Data exported to CSV successfully: " + csvFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
