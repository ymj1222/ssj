package com.service;

import com.dao.UserDao;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public List<User> query() {
        List<User> list= userDao.findAll();
        return list;
    }
    public void add(User user){
        userDao.save(user);
    }
    public List<User> getByName(String name){
        List<User> list = userDao.getByName(name);
        return list;
    }
    public void delete(String name){
        userDao.deleteByName(name);
    }
    public void update(String name,Integer id){
        userDao.updateName(name,id);
    }
    public User getById(Integer id){
        User user = userDao.getById(id);
        return user;
    }

}
