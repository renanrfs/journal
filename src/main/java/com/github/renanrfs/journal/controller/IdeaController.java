package com.github.renanrfs.journal.controller;

import com.github.renanrfs.journal.model.Idea;
import com.github.renanrfs.journal.service.IdeaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {

	@Autowired
	private IdeaService ideaService;

	@PostMapping
	public ResponseEntity< Idea > createIdea(@RequestBody Idea idea) {
		return ResponseEntity.ok(ideaService.createIdea(idea));
	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity< List<Idea> > getIdeasByProjectId(@PathVariable Long projectId) {
		return ResponseEntity.ok(ideaService.getIdeasByProjectId(projectId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Idea> updateIdea(@PathVariable Long id, @RequestBody Idea idea) {
		return ResponseEntity.ok(ideaService.updateIdea(id, idea));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteIdea(@PathVariable Long id) {
		ideaService.deleteIdea(id);
		return ResponseEntity.noContent().build();
	}
}

