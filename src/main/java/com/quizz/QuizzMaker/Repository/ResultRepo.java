package com.quizz.QuizzMaker.Repository;

import com.quizz.QuizzMaker.Model.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<Result , Long>  {

    Result findByQuizztitleAndUsername(String activeTitle, String userName);
}
