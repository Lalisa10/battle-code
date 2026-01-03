package com.example.battlecode_be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadSubmissionRequest {
    private String handle;
    private String problemCode;
    private String language;
}
