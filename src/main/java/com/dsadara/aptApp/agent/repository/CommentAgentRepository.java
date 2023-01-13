package com.dsadara.aptApp.agent.repository;

import com.dsadara.aptApp.agent.entity.CommentAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAgentRepository extends JpaRepository<CommentAgent, Long> {
}
