package com.quizz.QuizzMaker.Model;

import jakarta.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quizztitle;
    private String username;
    private int totalmarks;
    private int obtainedmarks;
    private String resultstatus;


    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuizztitle() {
        return quizztitle;
    }
    public void setQuizztitle(String quizztitle) {
        this.quizztitle = quizztitle;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getTotalmarks() {
        return totalmarks;
    }
    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }
    public int getObtainedmarks() {
        return obtainedmarks;
    }
    public void setObtainedmarks(int obtainedmarks) {
        this.obtainedmarks = obtainedmarks;
    }
    public String getResultstatus() {
        return resultstatus;
    }
    public void setResultstatus(String resultstatus) {
        this.resultstatus = resultstatus;
    }


}

