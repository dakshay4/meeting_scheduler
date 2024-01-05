package com.dakshay.beans;

import lombok.Data;


@Data
public class SlotBooking {

    private String id;
    private final Long startTime;
    private final Long endTime;

    public SlotBooking(Long startTime, Long endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
