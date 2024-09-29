package com.github.renanrfs.journal.service;

import com.github.renanrfs.journal.model.Idea;
import com.github.renanrfs.journal.model.Project;
import com.github.renanrfs.journal.repository.IdeaHistoryRepository;
import com.github.renanrfs.journal.repository.IdeaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IdeaServiceTest {

	@Mock
	private IdeaRepository ideaRepository;

	@Mock
	private IdeaHistoryRepository ideaHistoryRepository;

	@InjectMocks
	private IdeaService ideaService;

	private Idea idea;
	private Project project;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		project = new Project();
		project.setId(1L);

		idea = new Idea();
		idea.setId(1L);
		idea.setTitle("Test Idea");
		idea.setContent("Test content idea");
		idea.setCreatedAt(LocalDateTime.now());
		idea.setProject(project);
	}

	@Test
	void createIdea() {
		when(ideaRepository.save(any(Idea.class))).thenReturn(idea);
		Idea createdIdea = ideaService.createIdea(idea);

		assertNotNull(createdIdea);
		assertEquals("Test Idea", createdIdea.getTitle());
		assertEquals("Test content idea", createdIdea.getContent());
		verify(ideaRepository, times(1)).save(any(Idea.class));
	}

	@Test
	void getIdeasByProjectId() {
		when(ideaRepository.findByProjectId(1L)).thenReturn(List.of(idea));

		List<Idea> ideas = ideaService.getIdeasByProjectId(1L);
		assertFalse(ideas.isEmpty());
		assertEquals(1, ideas.size());
		verify(ideaRepository, times(1)).findByProjectId(1L);
	}

	@Test
	void updateIdea() {
		when(ideaRepository.findById(1L)).thenReturn(Optional.of(idea));
		when(ideaRepository.save(any(Idea.class))).thenReturn(idea);

		Idea updatedIdea = ideaService.updateIdea(1L, idea);
		assertNotNull(updatedIdea);
		verify(ideaRepository, times(1)).save(idea);
		verify(ideaHistoryRepository, times(1)).save(any());
	}
}

