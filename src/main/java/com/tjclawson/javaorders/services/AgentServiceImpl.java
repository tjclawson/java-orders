package com.tjclawson.javaorders.services;

import com.tjclawson.javaorders.models.Agent;
import com.tjclawson.javaorders.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("agentService")
public class AgentServiceImpl implements AgentService {

    private AgentRepository agentRepository;

    private AgentServiceImpl(AgentRepository agentRepository) { this.agentRepository = agentRepository; }

    @Override
    public Agent getAgentById(long id) {
        return agentRepository.findById(id);
    }
}
