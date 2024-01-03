package com.dakshay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MeetingScheduler {
    public static void main(String[] args) {
        SpringApplication.run(MeetingScheduler.class, args);
    }

}

/*

private static Map<Date, Date> bookedSlot = new HashMap<>();
    private static Integer threshold = 30 ;

    public static void main(String[] args) {

        Date fromTime = new Date();
        Date toTime = new Date();
        boolean ableToBookSLot = bookSlot(fromTime, toTime);

        if(!ableToBookSLot) {
            List<Map<Date, Date>> predictions = predictSlots(fromTime, toTime);
        }

    }

    private static List<Map<Date, Date>> predictSlots(Date fromTime, Date toTime) {
        int fromHour = fromTime.getHours();
        int toHour = toTime.getHours();


    }


    public static boolean bookSlot(Date startReqTime, Date endReqTime) {
        for(Map.Entry<Date, Date> m : bookedSlot.entrySet()) {
            Date from = (Date)m.getKey();
            Date to = (Date)m.getValue();
            if(startReqTime.compareTo(from) > 0 && startReqTime.compareTo(to) < 0) return false;
            if(endReqTime.compareTo(from) > 0 && endReqTime.compareTo(to) < 0) return false;
        }
        return true;
    }

 K(startTime of booked slot)     v(end time time of booked slot)

 1:00                           1:30
 2:30                           3:30
 3:30                           4:30

3:00(startReqTime) - 4:00(endReqTime)
1:30 - 2:30 => true;


iterate on hashmap of size n
key -> 1:00 < startReqTime && value -> 1:30 < endReqTime  continue;

startReqTime > 2:30 (key) && startReqTime < 3:30 (value)  => false;
endReqTime > 2:30 (key && startReqTime < 3:30 (value) => false;


startReqTime < 3:30(Key)  && endReqTime < 3:30 (Key)  => true;


3-4


3:00  - 3:30 => F
2:30 -3:00 => T
2:00 - 2:30 => F
1:30 - 2:30 => T






 */