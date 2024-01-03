package com.dakshay;


import com.dakshay.beans.SlotBooking;
import com.dakshay.services.SlotBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired private SlotBookingService slotBookingService;

    @PostMapping("/bookSlot")
    public boolean bookSlot(@RequestBody SlotBooking slotBooking) {
        return slotBookingService.bookSlot(slotBooking);
    }


    @GetMapping("/all")
    public List<SlotBooking> getAll() {
        return slotBookingService.getAll();
    }

}
