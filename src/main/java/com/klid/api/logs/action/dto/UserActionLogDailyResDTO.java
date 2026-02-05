package com.klid.api.logs.action.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserActionLogDailyResDTO {
    private String timeSlot;
    private int sumCount;
}
