package com.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.Account;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer>{
Account findByAccount(String account);
	Page<Account>findByNameContaining(String name, Pageable pageable);
Account findByCode(String code);
    Account findByAccountAndPasswordAndState(String account,String password,String state);
    Account findByAccountAndPassword(String account,String password);
    @Modifying
    @Transactional
    @Query("update Account set state=?1 where code=?2")
    void delete(String state,String code);
    @Modifying
    @Transactional
    @Query("update Account set password=?1 where account=?2")
    void update(String password,String account);
}
