package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * 기간 미만/초과 검사
     * @param startDate (yyyy-MM-dd)
     * @param endDate (yyyy-MM-dd)
     * @param day (두 날짜 사이의 차이가 day보다 크면 true 리턴)
     * @return boolean
     */
    public static boolean isGapGreaterThan(String startDate, String endDate, long day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDateStart = LocalDate.parse(startDate, formatter);
        LocalDate localDateEnd = LocalDate.parse(endDate, formatter);
        return localDateStart.compareTo(localDateEnd) > day;
    }
}
