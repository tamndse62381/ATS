package com.ats.emails;

import lombok.Data;

@Data
public class EmailRequest {
    private String name;
    private String to;
    private String from;
    private String subject;
}
