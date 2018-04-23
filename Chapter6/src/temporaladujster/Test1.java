package temporaladujster;

import org.junit.Test;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Test1 {
    @Test
    public void test1() {
        TemporalAdjuster NEXT_WORKDAY = w -> {
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };
        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate backToWork = today.with(NEXT_WORKDAY);
        System.out.println(backToWork);
    }

    @Test
    public void test2() {
        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w ->{
            LocalDate result = w;   //No cast
            do {
                result = result.plusDays(1);
            }
            while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });

        LocalDate today = LocalDate.now();
        System.out.println(today);
        LocalDate backToWork = today.with(NEXT_WORKDAY);
        System.out.println(backToWork);
    }
}
