package com.quizz.QuizzMaker.Service;

import com.quizz.QuizzMaker.Model.Question;
import com.quizz.QuizzMaker.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    public Question saveQuestion(Question question){
        return questionRepo.save(question);
    }

    public List<Question> getQuestionsByQuizzTitle(String quizz_title){
        return questionRepo.findByQuizzTitle(quizz_title);
    }

    public List<Question> getQuizzTitleByUsername(String userName) {
        return questionRepo.findByUsername(userName);
    }

    public List<Question> getAllQuizzTitles() {
        return questionRepo.findAll();
    }
}
