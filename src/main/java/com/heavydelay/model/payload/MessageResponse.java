package com.heavydelay.model.payload;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MessageResponse implements Serializable{
    private String message;
    private Integer status;
    private Object objectResponse;
}
