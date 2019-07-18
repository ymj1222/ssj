package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.AccountAuth;

@Repository
public interface AccountAuthDao extends JpaRepository<AccountAuth, Integer> {
List<AccountAuth>findByAccount(String account);
AccountAuth findByAccountAndAuthCode(String account,String authCode);
}
