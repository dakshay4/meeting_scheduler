package com.dakshay.services;


import com.dakshay.beans.SlotBooking;
import com.dakshay.dao.SlotBookingDao;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SlotBookingService {

    private final SlotBookingDao slotBookingDao;

    private static final Integer TOTAL_EPOCH_MS = 86400000;
    private static final Integer MIN_THRESHOLD_MS  = 1800000 ; // 30 Mins

    public SlotBookingService() {
        this.slotBookingDao = SlotBookingDao.getInstance();
    }

    public boolean bookSlot(SlotBooking slotBooking) {
       boolean res = slotBookingDao.canBookSlot(slotBooking);
       if(!res) {
           predictSlots(slotBooking, 5);
       }
    }

    private void predictSlots(SlotBooking slotBooking, int i) {
        Long startTime = slotBooking.getStartTime();
        Long endTime = slotBooking.getStartTime();
        Long intervalOfSlot = endTime - startTime;
        Long startOfDay = startTime - (startTime % TOTAL_EPOCH_MS);
        List<SlotBooking> set = sliceIntervalInDay(startOfDay, intervalOfSlot);

    }

    private List<SlotBooking> sliceIntervalInDay(Long startOfDay, Long intervalOfSlot) {
        List<SlotBooking> test = new ArrayList<>();
        while (startOfDay <= (startOfDay+TOTAL_EPOCH_MS)) {
            SlotBooking slotBooking = new SlotBooking();
            slotBooking.setStartTime(startOfDay);
            slotBooking.setEndTime(startOfDay+intervalOfSlot);
            test.add(slotBooking);
            startOfDay+=MIN_THRESHOLD_MS;
        }
        return test;
    }

    public List<SlotBooking> getAll() {
        return slotBookingDao.getSlotBookings();
    }
}
