package com.dao;

import com.entity.Account;
import com.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

        @Query("select a from Account a where a.account =?1")
        public List<Account> queryByAccount(String account);
        @Query("select a from Account a where a.account =?1 and a.password=?2")
        public Verification login(String account,String password);
        @Transactional
        @Modifying
        @Query("UPDATE  Account set state =?1 where code=?2")
        public void delete(String state,String code);

        @Transactional
        @Modifying
        @Query("update Account u set u.name = ?1 where u.id=?2")
        public int updateName(String name, Integer id);

}
