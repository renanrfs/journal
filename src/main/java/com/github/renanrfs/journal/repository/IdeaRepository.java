package com.github.renanrfs.journal.repository;

import com.github.renanrfs.journal.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
	List< Idea > findByProjectId(Long projectId);
}

