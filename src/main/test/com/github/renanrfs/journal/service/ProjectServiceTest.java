package com.github.renanrfs.journal.service;

import com.github.renanrfs.journal.repository.ProjectRepository;
import com.github.renanrfs.journal.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;

	@InjectMocks
	private ProjectService projectService;

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
		when(projectRepository.save(any(Project.class))).thenReturn(project);
		Project createdProject = projectService.createProject(project);

		assertNotNull(createdProject);
		assertEquals("Project Name", createdProject.getName());
		assertEquals("Test project description", createdProject.getDescription());
		verify(projectRepository, times(1)).save(any(Project.class));
	}

	@Test
	void getProjectById() {
		when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

		Optional<Project> foundProject = projectService.getProjectById(1L);
		assertTrue(foundProject.isPresent());
		assertEquals("Project Name", foundProject.get().getName());
		assertEquals("Test project description", foundProject.get().getDescription());
		verify(projectRepository, times(1)).findById(1L);
	}

	@Test
	void deleteProject() {
		doNothing().when(projectRepository).deleteById(1L);
		projectService.deleteProject(1L);

		verify(projectRepository, times(1)).deleteById(1L);
	}
}

