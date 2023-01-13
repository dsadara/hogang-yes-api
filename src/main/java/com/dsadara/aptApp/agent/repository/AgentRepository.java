package com.dsadara.aptApp.agent.repository;

import com.dsadara.aptApp.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
