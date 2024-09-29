package com.github.renanrfs.journal.controller;

import com.github.renanrfs.journal.model.Idea;
import com.github.renanrfs.journal.service.IdeaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdeaControllerTest {

	@Mock
	private IdeaService ideaService;

	@InjectMocks
	private IdeaController ideaController;

	private Idea idea;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		idea = new Idea();
		idea.setId(1L);
		idea.setTitle("Test Idea");
		idea.setContent("Content test idea");
		idea.setCreatedAt(LocalDateTime.now());
	}

	@Test
	void createIdea() {
		when(ideaService.createIdea(any(Idea.class))).thenReturn(idea);

		ResponseEntity<Idea> response = ideaController.createIdea(idea);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Test Idea", response.getBody().getTitle());
		assertEquals("Content test idea", response.getBody().getContent());
		verify(ideaService, times(1)).createIdea(any(Idea.class));
	}

	@Test
	void getIdeasByProjectId() {
		when(ideaService.getIdeasByProjectId(1L)).thenReturn(List.of(idea));

		ResponseEntity<List<Idea>> response = ideaController.getIdeasByProjectId(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertFalse(response.getBody().isEmpty());
		assertEquals(1, response.getBody().size());
		verify(ideaService, times(1)).getIdeasByProjectId(1L);
	}

	@Test
	void updateIdea() {
		when(ideaService.updateIdea(eq(1L), any(Idea.class))).thenReturn(idea);

		ResponseEntity<Idea> response = ideaController.updateIdea(1L, idea);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Test Idea", response.getBody().getTitle());
		assertEquals("Content test idea", response.getBody().getContent());
		verify(ideaService, times(1)).updateIdea(eq(1L), any(Idea.class));
	}

	@Test
	void deleteIdea() {
		doNothing().when(ideaService).deleteIdea(1L);

		ResponseEntity<Void> response = ideaController.deleteIdea(1L);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
		verify(ideaService, times(1)).deleteIdea(1L);
	}
}
