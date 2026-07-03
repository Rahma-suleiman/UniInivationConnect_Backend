package com.uni.innovationConnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uni.innovationConnect.dto.IdeaDTO;
import com.uni.innovationConnect.service.IdeaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/innovationConnect/idea")
@RequiredArgsConstructor
public class IdeaController {
    private final IdeaService ideaService;

     @GetMapping
    public ResponseEntity<List<IdeaDTO>> getAllIdeas() {
        List<IdeaDTO> ideas = ideaService.getAllIdeas();
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeaDTO> getIdeaById(@PathVariable Long id) {
        IdeaDTO idea = ideaService.getIdeaById(id);
        return ResponseEntity.ok(idea);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<IdeaDTO> getideaById(@PathVariable Long id) {
    //     return ResponseEntity.ok(ideaService.getideaById(id));
    // }

    @PostMapping
    public ResponseEntity<IdeaDTO> createIdea(@RequestBody IdeaDTO dto) {
        IdeaDTO idea = ideaService.createIdea(dto);
        // return new ResponseEntity<>(idea, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(idea);

    }

    @PutMapping("/{id}")
    public ResponseEntity<IdeaDTO> editIdea(@PathVariable Long id, @RequestBody IdeaDTO dto) {
        IdeaDTO idea = ideaService.editIdea(id, dto);
        return ResponseEntity.ok(idea);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIdea(@PathVariable Long id) {
        ideaService.deleteIdea(id);
        return ResponseEntity.noContent().build();
    }

}
