package com.github.renanrfs.journal.repository;

import com.github.renanrfs.journal.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository< Project, Long> {
}

