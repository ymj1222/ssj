package com.dao;

import com.entity.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AgentDao extends JpaRepository<Agent,Integer>, JpaSpecificationExecutor<Agent> {
    Agent getById(int id);
    List<Agent> getByTerminalCode(String terminalCode);
    Page<Agent> getByNameContainingOrderByIdDesc(String name, Pageable pageable);
}
