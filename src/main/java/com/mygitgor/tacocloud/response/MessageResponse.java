package com.mygitgor.tacocloud.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String massage;

    public String getMessage() {
        return massage;
    }
}
