package com.quizz.QuizzMaker.Service;

import com.quizz.QuizzMaker.Model.Result;
import com.quizz.QuizzMaker.Repository.ResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    @Autowired
    ResultRepo resultRepo;

    public Result saveResult(Result result) {
        return resultRepo.save(result);
    }

    public Result getResultByTitleAndUsername(String activeTitle, String userName) {
        return resultRepo.findByQuizztitleAndUsername(activeTitle, userName);
    }

    public void updateResult(int obtainedMarks , String resultStatus, Long id){
        Result result = resultRepo.getById(id);
        result.setResultstatus(resultStatus);
        result.setObtainedmarks(obtainedMarks);
        resultRepo.save(result);
    }
}
