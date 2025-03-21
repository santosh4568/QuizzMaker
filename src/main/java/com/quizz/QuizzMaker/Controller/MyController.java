package com.quizz.QuizzMaker.Controller;

import com.quizz.QuizzMaker.Model.Question;
import com.quizz.QuizzMaker.Model.Result;
import com.quizz.QuizzMaker.Model.User;
import com.quizz.QuizzMaker.Service.QuestionService;
import com.quizz.QuizzMaker.Service.ResultService;
import com.quizz.QuizzMaker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class MyController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResultService resultService;

    private String user_name = null;
    private String UserName = null;
    private String activeTitle = null;
    private String activeRole = null;
    private int questionCount = 1;
    private int noOfQuestions = 0;
    private int currentQuestion = 0;
    private String answerMarked = "";

    @GetMapping("/")
    public ModelAndView loadHome(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role" , activeRole);
        mv.addObject("user" , user_name);
        answerMarked = "";
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/signup")
    public ModelAndView loadSignUp(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("signup");
        return mv;
    }

    @PostMapping("/signup")
    public ModelAndView signUpNext(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") String ph,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("frole") String role)
    {
        ModelAndView mv = new ModelAndView();
        if(userService.usernameExists(username)){
            mv.addObject("usernameExists" , "Entered Username exists!!");
            mv.setViewName("signup");
            return mv;
        }
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        user.setPhone(ph);
        user.setRole(role);

        userService.SaveUser(user);

        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("login");
        return mv;
    }

    @GetMapping("/login")
    public ModelAndView loadLogin(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginNext(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("frole") String role
    ){
        ModelAndView mv = new ModelAndView();
        if(!userService.usernameExists(username)){
            mv.setViewName("signup");
            return mv;
        }
        User existed_user = userService.findByUsername(username);
        if(!existed_user.getPassword().equals(password)){
            mv.addObject("wrongPassword","You have entered wrong password");
            mv.setViewName("login");
            return mv;
        }
        if(!existed_user.getRole().equals(role)){
            mv.addObject("wrongRole","You have entered wrong role");
            mv.setViewName("login");
            return mv;
        }
        activeRole = role;
        UserName = username;
        user_name = userService.findNameByUsername(username);
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/createQuizz")
    public ModelAndView loadTitlePage(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("titleSelection");
        return mv;
    }

    @PostMapping("/addQuestions")
    public ModelAndView TitlePageNext(@RequestParam("quizTitle") String title){
        ModelAndView mv = new ModelAndView();
        String titleExists = !questionService.getQuestionsByQuizzTitle(title).isEmpty() ? "Title already exists" : "";
        if(titleExists.equals("Title already exists")){
            mv.addObject("user",user_name);
            mv.addObject("titleExists" , titleExists);
            mv.setViewName("titleSelection");
            return mv;
        }


        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        activeTitle = title;
        mv.addObject("questionCount" , "Question : "+questionCount);
        mv.setViewName("addQuestion");
        return mv;
    }

    @PostMapping("/saveQuestions")
    public ModelAndView addQuestions(
            @RequestParam("questions") String question,
            @RequestParam("A") String option1,
            @RequestParam("B") String option2,
            @RequestParam("C") String option3,
            @RequestParam("D") String option4,
            @RequestParam("Correct") String correctOption)
    {
        ModelAndView mv = new ModelAndView();
        Question qs = new Question();
        qs.setQuestion_desc(question);
        qs.setCorrect_option(correctOption);
        qs.setOption1(option1);
        qs.setOption2(option2);
        qs.setOption3(option3);
        qs.setOption4(option4);
        qs.setQuizz_title(activeTitle);
        qs.setUsername(UserName);

        questionService.saveQuestion(qs);

        questionCount+=1;
        List<Question> qsList = questionService.getQuestionsByQuizzTitle(activeTitle);
        mv.addObject("previousQuestionList",qsList);
        mv.addObject("user",user_name);
        mv.addObject("questionCount" , "Question : "+questionCount);
        mv.setViewName("addQuestion");
        return mv;
    }

    @PostMapping("/quizzSaved")
    public ModelAndView quizzSaved(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("Home");
        return mv;
    }

    @GetMapping("/viewQuizz")
    public ModelAndView loadPreviousQuizz(){
        ModelAndView mv = new ModelAndView();
        List<Question> titleList = questionService.getQuizzTitleByUsername(UserName);
        Set<String> titleSet = new HashSet<>();
        for(Question q : titleList){
            titleSet.add(q.getQuizz_title());
        }
        mv.addObject("TitleList",titleSet);
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("previousQuizz");
        return mv;
    }

    @PostMapping("/prevQuizz")
    public ModelAndView previousQuizzNext(@RequestParam("quizz") String title){
        ModelAndView mv = new ModelAndView();
        List<Question> titleList = questionService.getQuizzTitleByUsername(UserName);
        Set<String> titleSet = new HashSet<>();
        for(Question q : titleList){
            titleSet.add(q.getQuizz_title());
        }
        mv.addObject("TitleList",titleSet);
        List<Question> qsList = questionService.getQuestionsByQuizzTitle(title);
        mv.addObject("previousQuestionList",qsList);
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.setViewName("previousQuizz");
        return mv;
    }

    @GetMapping("/selectTitle")
    public ModelAndView loadTitlePageUser(){
        ModelAndView mv = new ModelAndView();
        List<Question> titleList = questionService.getAllQuizzTitles();
        Set<String> titleSet = new HashSet<>();
        for(Question q : titleList){
            titleSet.add(q.getQuizz_title());
        }
        mv.addObject("TitleList",titleSet);
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        answerMarked = "";
        mv.setViewName("titleSelectionUser");
        return mv;
    }

    @PostMapping("/takeQuizz")
    public ModelAndView loadQuizzPage(@RequestParam("quizz") String title){
        ModelAndView mv = new ModelAndView();
        List<Question> qsList = questionService.getQuestionsByQuizzTitle(title);
        activeTitle = title;
        currentQuestion = 0;
        noOfQuestions = qsList.size();
        mv.addObject("question",qsList.get(currentQuestion));
        currentQuestion++;
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        if(currentQuestion == noOfQuestions){
            mv.addObject("lastQuestion",true);
        }
        else{
            mv.addObject("lastQuestion",false);
        }
        //answerMarked += qsList.get(currentQuestion).getId()+":"+answer;
        mv.setViewName("quizzPage");
        return mv;
    }


    @PostMapping("/submitQuiz")
    public ModelAndView quizzPageNext(@RequestParam("option") String answer){
        ModelAndView mv = new ModelAndView();
        List<Question> qsList = questionService.getQuestionsByQuizzTitle(activeTitle);
        if(currentQuestion < noOfQuestions){mv.addObject("question",qsList.get(currentQuestion));}
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        if(currentQuestion == noOfQuestions){
            mv.addObject("Role" , activeRole);
            mv.addObject("user",user_name);
            answerMarked += qsList.get(currentQuestion-1).getId()+":"+answer;
            Result result = new Result();
            result.setMarkedanswers(answerMarked);
            result.setQuizztitle(activeTitle);
            result.setTotalmarks(noOfQuestions);
            result.setUsername(UserName);

            resultService.saveResult(result);

            mv.setViewName("preresult");
            return mv;
        }
        else{
            if(currentQuestion == noOfQuestions - 1){
                mv.addObject("lastQuestion",true);
            }
            else {
                mv.addObject("lastQuestion", false);
            }
            mv.setViewName("quizzPage");
        }
        answerMarked += qsList.get(currentQuestion-1).getId()+":"+answer+"#";
        currentQuestion++;
        return mv;
    }

    @PostMapping("/Result")
    public ModelAndView loadResult(){
        ModelAndView mv = new ModelAndView();
        Map<Long, String> markedAnswersMap = new HashMap<>();
        Result result = resultService.getResultByTitleAndUsername(activeTitle,UserName);
        List<Question> qsList = questionService.getQuestionsByQuizzTitle(result.getQuizztitle());
        int marksObtained = 0;
        String[] markedAnswers = result.getMarkedanswers().split("#");

        for (String s : markedAnswers) {
            String[] temp = s.split(":");
            markedAnswersMap.put(Long.parseLong(temp[0]), temp[1]);
        }

        for(String s : markedAnswers){
            String[] temp = s.split(":");
            for(Question q : qsList){
                if(q.getId() == Long.parseLong(temp[0])){
                    if(q.getCorrect_option().equals(temp[1])){
                        marksObtained++;
                    }
                }
            }
        }
        double percentage = marksObtained / (double)qsList.size() * 100;
        if(percentage >= 50){
            result.setResultstatus("Pass");
        }
        else{
            result.setResultstatus("Fail");
        }
        result.setObtainedmarks(marksObtained);
        resultService.updateResult(marksObtained,result.getResultstatus(),result.getId());
        mv.addObject("Role" , activeRole);
        mv.addObject("user",user_name);
        mv.addObject("result",result);
        mv.addObject("question" ,qsList);
        mv.addObject("markedAnswersMap", markedAnswersMap);
        mv.setViewName("showResult");
        return mv;
    }


    @GetMapping("/logout")
    public ModelAndView Logout(){
        user_name = null;
        activeRole = null;
        noOfQuestions = 0;
        currentQuestion = 0;
        questionCount = 1;
        answerMarked = "";
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Home");
        return mv;
    }


}
