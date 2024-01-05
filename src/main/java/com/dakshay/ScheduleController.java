package com.dakshay;


import com.dakshay.beans.SlotBookVO;
import com.dakshay.beans.SlotBooking;
import com.dakshay.beans.SlotsAvailableUI;
import com.dakshay.services.SlotBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired private SlotBookingService slotBookingService;

    @PostMapping("/bookSlot")
    public boolean bookSlot(@RequestBody SlotBooking slotBooking) {
        return slotBookingService.bookSlot(slotBooking);
    }


    @GetMapping("/all")
    public List<SlotsAvailableUI> getAll() {
        List<SlotBooking> slotBookings = slotBookingService.getAll();
        return slotBookings.stream().map(e->getTime(e)).collect(Collectors.toList());

    }


    @GetMapping("/slices")
    public List<SlotsAvailableUI> slices(@RequestBody SlotBooking slotBooking) {
        List<SlotBooking> res = slotBookingService.predictSlots(slotBooking,10);
        return res.stream().map(e->getTime(e)).collect(Collectors.toList());
    }

    private SlotsAvailableUI getTime(SlotBooking e) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("IST"));
        return new SlotsAvailableUI(sdf.format(new Date(e.getStartTime())), sdf.format(new Date(e.getEndTime())));
    }

}
