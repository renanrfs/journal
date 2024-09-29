package com.github.renanrfs.journal.controller;

import com.github.renanrfs.journal.model.Project;
import com.github.renanrfs.journal.service.ProjectService;
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

class ProjectControllerTest {

	@Mock
	private ProjectService projectService;

	@InjectMocks
	private ProjectController projectController;

	private Project project;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		project = new Project();
		project.setId(1L);
		project.setName("Project Name");
		project.setDescription("Test project description");
		project.setCreatedAt(LocalDateTime.now());
	}

	@Test
	void createProject() {
		when(projectService.createProject(any(Project.class))).thenReturn(project);

		ResponseEntity<Project> response = projectController.createProject(project);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Project Name", response.getBody().getName());
		assertEquals("Test project description", response.getBody().getDescription());
		verify(projectService, times(1)).createProject(any(Project.class));
	}

	@Test
	void getProjectById() {
		when(projectService.getProjectById(1L)).thenReturn(Optional.of(project));

		ResponseEntity<Project> response = projectController.getProjectById(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Project Name", response.getBody().getName());
		assertEquals("Test project description", response.getBody().getDescription());
		verify(projectService, times(1)).getProjectById(1L);
	}

	@Test
	void getAllProjects() {
		when(projectService.getAllProjects()).thenReturn(List.of(project));

		ResponseEntity<List<Project>> response = projectController.getAllProjects();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertFalse(response.getBody().isEmpty());
		verify(projectService, times(1)).getAllProjects();
	}
}

