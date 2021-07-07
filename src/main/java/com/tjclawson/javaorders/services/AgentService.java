package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentService {

    Agent getAgentById(long id);
}
