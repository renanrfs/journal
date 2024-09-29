package com.github.renanrfs.journal.service;

import com.github.renanrfs.journal.model.Idea;
import com.github.renanrfs.journal.model.IdeaHistory;
import com.github.renanrfs.journal.repository.IdeaHistoryRepository;
import com.github.renanrfs.journal.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

	@Autowired
	private IdeaRepository ideaRepository;

	@Autowired
	private IdeaHistoryRepository ideaHistoryRepository;

	public Idea createIdea(Idea idea) {
		idea.setCreatedAt( LocalDateTime.now());
		return ideaRepository.save(idea);
	}

	public List<Idea> getIdeasByProjectId(Long projectId) {
		return ideaRepository.findByProjectId(projectId);
	}

	public Idea updateIdea(Long id, Idea newIdea) {
		Optional<Idea> existingIdea = ideaRepository.findById(id);
		if (existingIdea.isPresent()) {
			Idea idea = existingIdea.get();
			saveIdeaHistory(idea);
			idea.setContent(newIdea.getContent());
			return ideaRepository.save(idea);
		}
		return null;
	}

	private void saveIdeaHistory(Idea idea) {
		IdeaHistory history = new IdeaHistory();
		history.setContentBeforeChange(idea.getContent());
		history.setChangedAt(LocalDateTime.now());
		history.setIdea(idea);
		ideaHistoryRepository.save(history);
	}

	public void deleteIdea(Long id) {
		ideaRepository.deleteById(id);
	}
}
