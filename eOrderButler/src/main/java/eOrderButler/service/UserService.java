package eOrderButler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eOrderButler.dao.UserDao;
import eOrderButler.model.User;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    public void addUser(User user) {   	 
   	 userDao.addUser(user);
    }
    //???? return type is String
    public String getUserByUserName(String userName) {
   	 return userDao.getUserByUserName(userName);
    }
}

