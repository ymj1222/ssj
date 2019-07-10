package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

        @Query("select u from User u where u.name like %?1% order by u.id desc")
        public List<User> getByName(String name);

        @Transactional
        @Modifying
        @Query("update User u set u.name = ?1 where u.id=?2")
        public int updateName(String name, Integer id);

        @Transactional
        int deleteByName(String name);

        @Query("select u from User u where u.id = ?1")
        public User  getById(Integer id);
}
