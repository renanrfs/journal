package com.github.renanrfs.journal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Idea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String content;
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "parent_idea_id")
	private Idea parentIdea;

	@OneToMany(mappedBy = "parentIdea", cascade = CascadeType.ALL)
	private List<Idea> subIdeas;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
	private List<IdeaHistory> history;

	// Getters e Setters
}

