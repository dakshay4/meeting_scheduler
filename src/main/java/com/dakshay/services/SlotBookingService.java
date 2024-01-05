package com.dakshay.services;


import com.dakshay.beans.SlotBooking;
import com.dakshay.dao.SlotBookingDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlotBookingService {

    private final SlotBookingDao slotBookingDao;

    private static final Integer TOTAL_EPOCH_MS_IN_A_DAY = 86400000;
    private static final Integer TOTAL_EPOCH_MS_IN_A_HOUR = 3600000;
    private static final Integer MIN_SLICE_MS = 1800000 ; // 30 Mins

    public SlotBookingService() {
        this.slotBookingDao = SlotBookingDao.getInstance();
    }

    public boolean bookSlot(SlotBooking slotBooking) {
       boolean res = slotBookingDao.canBookSlot(slotBooking);
        if (res) slotBookingDao.addSlot(slotBooking);
       return res;
    }

    public List<SlotBooking> predictSlots(SlotBooking slotBooking, int i) {
        Long startTime = slotBooking.getStartTime();
        Long endTime = slotBooking.getEndTime();
        Long intervalOfSlot = endTime - startTime;
        Long startOfDay = startTime - (startTime % TOTAL_EPOCH_MS_IN_A_DAY);
        List<SlotBooking> set = sliceIntervalInDay(startOfDay, intervalOfSlot);
        List<SlotBooking> res = new ArrayList<>();
        int predictionCount = 0;
        int alternator = 0;
        int totalSlotsAllowed = calculateIntervalsInTimeSlices(24, (int) (intervalOfSlot/TOTAL_EPOCH_MS_IN_A_HOUR), MIN_SLICE_MS/(60*1000));
        Long newStartMovingLeft = startTime;
        Long newStartMovingRight = startTime;
        while(predictionCount<i && alternator<totalSlotsAllowed) {
            newStartMovingLeft = newStartMovingLeft - MIN_SLICE_MS;
            newStartMovingRight = newStartMovingRight + MIN_SLICE_MS;
            SlotBooking toBookLeft =new SlotBooking(newStartMovingLeft, newStartMovingLeft + intervalOfSlot);
            SlotBooking toBookRight =new SlotBooking(newStartMovingRight, newStartMovingRight + intervalOfSlot);
            if(slotBookingDao.canBookSlot(toBookLeft)) {
                predictionCount++;
                res.add(toBookLeft);
            }
            if(slotBookingDao.canBookSlot(toBookRight)) {
                predictionCount++;
                res.add(toBookRight);
            }
            alternator+=2;
        }
        return res;
    }

    private static int calculateIntervalsInTimeSlices(
            int totalHoursInDay, int intervalDuration, int timeSlice) {
        int totalMinutesInDay = totalHoursInDay * 60;

        if (intervalDuration <= 0 || timeSlice <= 0) {
            return 0; // Invalid input parameters
        }

        int intervals = 0;

        for (int start = 0; start < totalMinutesInDay; start += timeSlice) {
            int end = start + intervalDuration;

            if (end <= totalMinutesInDay) {
                intervals++;
            }
        }

        return intervals;
    }

    private List<SlotBooking> sliceIntervalInDay(Long startOfDay, Long intervalOfSlot) {
        List<SlotBooking> test = new ArrayList<>();
        Long temp = startOfDay;
        while (temp < (startOfDay+ TOTAL_EPOCH_MS_IN_A_DAY)) {
            SlotBooking slotBooking = new SlotBooking(temp, temp+intervalOfSlot);
            test.add(slotBooking);
            temp+= MIN_SLICE_MS;
        }
        return test;
    }

    public List<SlotBooking> getAll() {
        return slotBookingDao.getSlotBookings();
    }
}
