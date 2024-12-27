package com.quizz.QuizzMaker.Service;

import com.quizz.QuizzMaker.Model.User;
import com.quizz.QuizzMaker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User SaveUser(User user){
        return userRepo.save(user);
    }

    public boolean usernameExists(String username){
        return userRepo.existsByUsername(username);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public String findNameByUsername(String username){
        return userRepo.findByUsername(username).getName();
    }

}
