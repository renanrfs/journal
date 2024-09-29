package com.github.renanrfs.journal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class IdeaHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String contentBeforeChange;
	private String contentAfterChange;
	private LocalDateTime changedAt;

	@ManyToOne
	@JoinColumn(name = "idea_id")
	private Idea idea;

	// Getters e Setters
}

