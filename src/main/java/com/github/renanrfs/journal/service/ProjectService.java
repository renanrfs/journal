package com.github.renanrfs.journal.service;

import com.github.renanrfs.journal.model.Project;
import com.github.renanrfs.journal.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public Project createProject(Project project) {
		project.setCreatedAt( LocalDateTime.now());
		return projectRepository.save(project);
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Optional<Project> getProjectById(Long id) {
		return projectRepository.findById(id);
	}

	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}
}

