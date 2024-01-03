package com.dakshay.dao;

import com.dakshay.beans.SlotBooking;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SlotBookingDao {

    private List<SlotBooking> slotBookings = new ArrayList<>();

    private static SlotBookingDao slotBookingDao;

    public static SlotBookingDao getInstance() {
        if(slotBookingDao == null) {
            slotBookingDao =  new SlotBookingDao();
        }
        return slotBookingDao;
    }

    public boolean canBookSlot(SlotBooking slotBookingRequest) {
        boolean canBookSlot = true;
        slotBookings.sort(Comparator.comparing(SlotBooking::getStartTime));
        for (SlotBooking slot : slotBookings) {
            if (slotBookingRequest.getStartTime().compareTo(slot.getStartTime()) > 0 && slotBookingRequest.getStartTime().compareTo(slot.getEndTime()) < 0)
                canBookSlot = false;
            if (slotBookingRequest.getEndTime().compareTo(slot.getStartTime()) > 0 && slotBookingRequest.getStartTime().compareTo(slot.getEndTime()) < 0)
                canBookSlot = false;
        }
        if (canBookSlot) addSlot(slotBookingRequest);
        return canBookSlot;
    }

    private void addSlot(SlotBooking slotBookingRequest) {
        slotBookings.add(slotBookingRequest);
    }

    public List<SlotBooking> getSlotBookings() {
        return slotBookings;
    }
}
