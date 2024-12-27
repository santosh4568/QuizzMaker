package com.quizz.QuizzMaker.Repository;

import com.quizz.QuizzMaker.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User , Long> {

    boolean existsByUsername(String username);

    User findByUsername(String username);
}
