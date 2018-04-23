package formatting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class Formatting {
    public static void main(String[] args){
        ZonedDateTime apollo11lanuch = ZonedDateTime.of(1969,7,16,9,32,0,0,
                ZoneId.of("America/New_York"));
        String formatted = DateTimeFormatter.ISO_OFFSET_DATE.format(apollo11lanuch);
        //1969-07-16T09:32:00-04:00
        System.out.println(formatted);

        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        formatted = formatter.format(apollo11lanuch);
        // July 16, 1969 9:32:00 AM EDT
        System.out.println(formatted);

        formatted = formatter.withLocale(Locale.FRENCH).format(apollo11lanuch);
        // 16 juillet 1969 09:32:00 EDT
        System.out.println(formatted);

        formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
        formatted = formatter.format(apollo11lanuch);
        System.out.println(formatted);

        LocalDate churchBirthday = LocalDate.parse("1903-06-14");
        System.out.println("churchsBirthday: " + churchBirthday);
        apollo11lanuch = ZonedDateTime.parse("1969-07-16 03:32:00-0400",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
        System.out.println("apollo11lanuch: "+ apollo11lanuch);

        for (DayOfWeek w: DayOfWeek.values()) {
            System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
        }
    }
}
