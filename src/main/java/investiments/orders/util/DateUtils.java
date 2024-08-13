package investiments.orders.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parseDate(String date){
        return LocalDate.from(DATE_TIME_FORMATTER.parse(date));
    }
}
