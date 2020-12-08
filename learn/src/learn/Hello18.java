package learn;

import java.text.MessageFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

// Format 클래스

public class Hello18 {

    public static <T> void print(T t) {
        System.out.println(t);
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        // 1. 숫자 형식 클래스 (DecimalFormat)
        // 2. 날짜 형식 클래스 (SimpleDataFormat)

        // 3. 문자열 형식 클래스 (MessageFormat)
        String msg = "Id : {0},\t이름 : {1},\t전화 : {2}";
        String ret = MessageFormat.format(msg, 123, "가나", " 010 - 3322- 1234");
        print(msg);
        print(ret + "\n");

        // 4. java.time 패키지
        LocalDate currDate = LocalDate.now(); // 현재 날짜 정보
        LocalDate targetDate = LocalDate.of(2020, 12, 25); // 타겟 날짜 정보
        LocalTime currTime = LocalTime.now(); // 현재 시간 정보
        LocalTime targetTime = LocalTime.of(23, 11); // 타겟 시간 정보
        LocalDateTime currDateTime = LocalDateTime.now(); // 현재 날짜와 시간 정보
        LocalDateTime targetDateTime = LocalDateTime.of(2025, 12, 25, 6, 32, 1, 1); // 타겟 날짜와 시간 정보
        print(currDate);
        print(targetDate);
        print(currTime);
        print(targetTime);
        print(currDateTime);
        print(targetDateTime + "\n");

        // 협정 세계시
        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nyDateTime = ZonedDateTime.now(ZoneId.of("America/New_York"));
        print(utcDateTime);
        print(nyDateTime + "\n");

        // 특정 시간 타임 스탬프 얻기
        Instant instant1 = Instant.now();
        Thread.sleep(10); // milli
        Instant instant2 = Instant.now();
        if (instant1.isAfter(instant2)) {
            print("instant2 -> instant1");
        } else if (instant1.isBefore(instant2)) {
            print("instant1 -> instant2");
        } else {
            print("instant1 == instant2");
        }
        print("차이 (nanos) : " + instant1.until(instant2, ChronoUnit.NANOS) + "\n");

        // 날짜, 시간 조작, plus, minus, with(변경)
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = now.plusYears(5).minusMonths(4).plusDays(23).minusHours(10).plusMinutes(23)
                .plusSeconds(23);
        print(now);
        print(target + "\n");

        // 이하 다양한 클래스와 메소드..
    }

}
