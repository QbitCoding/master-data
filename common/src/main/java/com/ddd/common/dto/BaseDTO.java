package com.ddd.common.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;


public abstract class BaseDTO implements Serializable {

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
