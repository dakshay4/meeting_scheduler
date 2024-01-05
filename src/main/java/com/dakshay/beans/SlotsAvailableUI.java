package com.dakshay.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class SlotsAvailableUI {

    private final String startTime;
    private final String endTime;
}
