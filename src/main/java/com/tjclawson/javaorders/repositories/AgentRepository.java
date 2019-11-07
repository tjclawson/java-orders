package com.tjclawson.javaorders.repositories;

import com.tjclawson.javaorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> {

    Agent findById(long agentcode);
}
