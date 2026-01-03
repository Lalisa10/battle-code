package com.example.battlecode_be.controller;

import com.example.battlecode_be.dto.ProblemResponse;
import com.example.battlecode_be.model.Problem;
import com.example.battlecode_be.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {
    private final ProblemService problemService;

    @GetMapping("/{code}")
    public ProblemResponse getProblem(@PathVariable String code) {
        return toResponse(problemService.getByCode(code));
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
