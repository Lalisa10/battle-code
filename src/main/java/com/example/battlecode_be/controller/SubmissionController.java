package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.SubmissionResponse;
import com.example.battlecode_be.dto.UploadSubmissionRequest;
import com.example.battlecode_be.model.Submission;
import com.example.battlecode_be.service.SubmissionService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public SubmissionResponse upload(
            @RequestPart("request") UploadSubmissionRequest request,
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        Submission sub = submissionService.createSubmission(
                request, file
        );

        return SubmissionResponse.builder()
                .submissionId(sub.getId())
                .handle(sub.getUser().getHandle())
                .problemCode(sub.getProblem().getCode())
                .language(sub.getLanguage())
                .codeUrl(sub.getCodeUrl())
                .build();
    }

    @GetMapping
    public List<SubmissionResponse> getSubmissionsByProblemCode(@RequestParam String problemCode) {
        return submissionService.getSubmissionsByProblemCode(problemCode);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getSubmissionFile(@PathVariable Long id) {

        Submission sub = submissionService.getSubmissionById(id);
       // System.out.printf("getSubmissionFile: %s\n", sub.getId());
        Path path = Paths.get(sub.getCodeUrl());
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(path);

        String filename = path.getFileName().toString();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + filename + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
