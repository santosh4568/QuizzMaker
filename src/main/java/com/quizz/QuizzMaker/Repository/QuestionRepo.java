package com.quizz.QuizzMaker.Repository;

import com.quizz.QuizzMaker.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findByQuizzTitle(String quizzTitle);

    List<Question> findByUsername(String userName);
}
