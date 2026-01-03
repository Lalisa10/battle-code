package com.example.battlecode_be.service;

import com.example.battlecode_be.dto.CreateProblemRequest;
import com.example.battlecode_be.model.Problem;
import com.example.battlecode_be.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final ProblemRepository problemRepository;

    public Problem createProblem(CreateProblemRequest request) {
        if (problemRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Problem code has already exist!");
        }
        Problem newProblem
                = Problem.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .engineType(request.getEngineType())
                .timeLimitMs(request.getTimeLimitMs())
                .memoryLimitMb(request.getMemoryLimitMb())
                .isActive(true)
                .build();
        return problemRepository.save(newProblem);
    }

    public Problem getByCode(String code) {
        return problemRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Problem not found!"));
    }
}
