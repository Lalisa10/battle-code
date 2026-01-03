package com.example.battlecode_be.service;

import com.example.battlecode_be.dto.UploadSubmissionRequest;
import com.example.battlecode_be.model.Problem;
import com.example.battlecode_be.model.Submission;
import com.example.battlecode_be.model.User;
import com.example.battlecode_be.repository.ProblemRepository;
import com.example.battlecode_be.repository.SubmissionRepository;
import com.example.battlecode_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.*;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;

    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

    public Submission getSubmissionById(Long id) {
        return submissionRepository.getReferenceById(id);
    }

    public Submission createSubmission(
            UploadSubmissionRequest request,
            MultipartFile file
    ) throws IOException {

        User user = userRepository.findByHandle(request.getHandle())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Problem problem = problemRepository.findByCode(request.getProblemCode())
                .orElseThrow(() -> new IllegalArgumentException("Problem not found"));

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file");
        }

        String ext = getExtension(request.getLanguage());
        if (!file.getOriginalFilename().endsWith(ext)) {
            throw new IllegalArgumentException("Invalid file extension");
        }

        // ----- Save file -----
        String subDir = "submissions/" + Year.now() + "/" + MonthDay.now().getMonthValue();
        Path dirPath = Paths.get(uploadDir, subDir);
        Files.createDirectories(dirPath);

        String filename = UUID.randomUUID() + ext;
        Path filePath = dirPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // ----- Save DB -----
        Submission submission = Submission.builder()
                .user(user)
                .problem(problem)
                .language(request.getLanguage())
                .codeUrl(filePath.toString())
                .build();

        return submissionRepository.save(submission);
    }

    private String getExtension(String language) {
        return switch (language.toLowerCase()) {
            case "python" -> ".py";
            case "cpp" -> ".cpp";
            case "java" -> ".java";
            default -> throw new IllegalArgumentException("Unsupported language");
        };
    }
}
