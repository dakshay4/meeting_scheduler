package com.dakshay.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlotBookVO {

    private boolean isSlotAvailable;
    private List<SlotBooking> availableSlots;
}
