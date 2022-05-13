package com.ddd.domain.event;

import com.ddd.common.event.AbstractEvent;
import lombok.Getter;



@Getter
public class ResidentCreatedEvent extends AbstractEvent {

    private String residentId;


    public ResidentCreatedEvent(String residentId) {
        super();
        // super();
        this.residentId = residentId;
    }




}
