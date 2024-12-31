package com.quizz.QuizzMaker.Model;

import jakarta.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question_desc;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;
    private String optionMarked = "Not Marked";
    private String quizzTitle;
    private String username;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuestion_desc() {
        return question_desc;
    }
    public void setQuestion_desc(String question_desc) {
        this.question_desc = question_desc;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        return option3;
    }
    public void setOption3(String option3) {
        this.option3 = option3;
    }
    public String getOption4() {
        return option4;
    }
    public void setOption4(String option4) {
        this.option4 = option4;
    }
    public String getCorrect_option() {
        return correctOption;
    }
    public void setCorrect_option(String correct_option) {
        this.correctOption = correct_option;
    }
    public String getOption_marked() {
        return optionMarked;
    }
    public void setOption_marked(String option_marked) {
        this.optionMarked = option_marked;
    }
    public String getQuizz_title() {
        return quizzTitle;
    }
    public void setQuizz_title(String quizz_title) {
        this.quizzTitle = quizz_title;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}

