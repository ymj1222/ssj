package com.dao;

import com.entity.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalDao extends JpaRepository<Terminal,Integer>, JpaSpecificationExecutor<Terminal> {
    Terminal getById(int id);
    Page<Terminal> getByNameContainingOrderByIdDesc(String name, Pageable pageable);
}
