package com.example.battlecode_be.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmissionResponse {
    private String handle;
    private String problemCode;
    private String language;
    private String codeUrl;
}

