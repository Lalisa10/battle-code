package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.CreateProblemRequest;
import com.example.battlecode_be.dto.ProblemResponse;
import com.example.battlecode_be.model.Problem;
import com.example.battlecode_be.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/{code}")
    public ProblemResponse getProblem(@PathVariable String code) {
        return toResponse(problemService.getByCode(code));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProblemResponse createProblem(@RequestBody CreateProblemRequest request) {
        Problem problem = problemService.createProblem(request);
        return toResponse(problem);
    }

    private ProblemResponse toResponse(Problem problem) {
        return ProblemResponse.builder()
                .code(problem.getCode())
                .name(problem.getName())
                .description(problem.getDescription())
                .timeLimitMs(problem.getTimeLimitMs())
                .memoryLimitMb(problem.getMemoryLimitMb()).build();
    }
}
