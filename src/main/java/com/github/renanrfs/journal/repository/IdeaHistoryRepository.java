package com.github.renanrfs.journal.repository;

import com.github.renanrfs.journal.model.IdeaHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaHistoryRepository extends JpaRepository< IdeaHistory, Long> {
}

