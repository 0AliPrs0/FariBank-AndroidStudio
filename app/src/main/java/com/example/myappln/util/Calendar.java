package ir.ac.kntu.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Calendar {
    public static final int TIME_SPEED = 6000;

    private static Instant start = Instant.now();

    private Calendar() {
    }

    public static Instant now() {
        return Instant.ofEpochMilli(start.toEpochMilli() + (Instant.now().toEpochMilli() - start.toEpochMilli()) * TIME_SPEED);
    }

    public static Instant trimTime(Instant time) {
        Pattern numP = Pattern.compile("[0-9]+");
        Matcher numM = numP.matcher(time.toString());
        String year = "", month = "", day = "";
        if (numM.find()) {
            year = time.toString().substring(numM.start(), numM.end());
            if (numM.find()) {
                month = time.toString().substring(numM.start(), numM.end());
                if (numM.find()) {
                    day = time.toString().substring(numM.start(), numM.end());
                }
            }
        }
        return Instant.parse(year + "-" + month + "-" + day + "T00:00:00.000Z");
    }

    public static int distance(Instant time1, Instant time2) {
        Pattern numberP = Pattern.compile("[0-9]+");
        Matcher numberM1 = numberP.matcher(time1.toString());
        Matcher numberM2 = numberP.matcher(time2.toString());
        int year1 = 0, month1 = 0, day1 = 0, year2 = 0, month2 = 0, day2 = 0;
        if (numberM1.find()) {
            year1 = Integer.parseInt(time1.toString().substring(numberM1.start(), numberM1.end()));
            if (numberM1.find()) {
                month1 = Integer.parseInt(time1.toString().substring(numberM1.start(), numberM1.end()));
                if (numberM1.find()) {
                    day1 = Integer.parseInt(time1.toString().substring(numberM1.start(), numberM1.end()));
                }
            }
        }
        if (numberM2.find()) {
            year2 = Integer.parseInt(time2.toString().substring(numberM2.start(), numberM2.end()));
            if (numberM2.find()) {
                month2 = Integer.parseInt(time2.toString().substring(numberM2.start(), numberM2.end()));
                if (numberM2.find()) {
                    day2 = Integer.parseInt(time2.toString().substring(numberM2.start(), numberM2.end()));
                }
            }
        }
        return Math.abs((int) ChronoUnit.DAYS.between(LocalDate.of(year2, month2, day2), LocalDate.of(year1, month1, day1)));
    }

    public static Instant nextMonth(Instant time) {
        return time.plus(30, ChronoUnit.DAYS);
    }

    public static int daysBetween(Instant time, Instant nextTime) {
        Pattern numP = Pattern.compile("[0-9]+");
        Matcher numM1 = numP.matcher(time.toString());
        Matcher numM2 = numP.matcher(nextTime.toString());
        int year1 = 0, year2 = 0, month1 = 0, month2 = 0, day1 = 0, day2 = 0;
        if (numM1.find()) {
            year1 = Integer.parseInt(time.toString().substring(numM1.start(), numM1.end()));
            if (numM1.find()) {
                month1 = Integer.parseInt(time.toString().substring(numM1.start(), numM1.end()));
                if (numM1.find()) {
                    day1 = Integer.parseInt(time.toString().substring(numM1.start(), numM1.end()));
                }
            }
        }
        if (numM2.find()) {
            year2 = Integer.parseInt(nextTime.toString().substring(numM2.start(), numM2.end()));
            if (numM2.find()) {
                month2 = Integer.parseInt(nextTime.toString().substring(numM2.start(), numM2.end()));
                if (numM2.find()) {
                    day2 = Integer.parseInt(nextTime.toString().substring(numM2.start(), numM2.end()));
                }
            }
        }
        return (int) ChronoUnit.DAYS.between(LocalDate.of(year1, month1, day1), LocalDate.of(year2, month2, day2));
    }
}
