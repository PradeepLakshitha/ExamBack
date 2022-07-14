package com.exam.examserver.model.exam;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ques_id;

    @Column(length = 5000)
    private String ques_content;

    private String ques_image;

    private String ques_option1;
    private String ques_option2;
    private String ques_option3;
    private String ques_option4;

    private String ques_answer;

    public Question() {
    }

    public Question(String ques_content, String ques_image, String ques_option1, String ques_option2, String ques_option3, String ques_option4, String ques_answer, Quiz quiz) {
        this.ques_content = ques_content;
        this.ques_image = ques_image;
        this.ques_option1 = ques_option1;
        this.ques_option2 = ques_option2;
        this.ques_option3 = ques_option3;
        this.ques_option4 = ques_option4;
        this.ques_answer = ques_answer;
        this.quiz = quiz;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;

    public Long getQues_id() {
        return ques_id;
    }

    public void setQues_id(Long ques_id) {
        this.ques_id = ques_id;
    }

    public String getQues_content() {
        return ques_content;
    }

    public void setQues_content(String ques_content) {
        this.ques_content = ques_content;
    }

    public String getQues_image() {
        return ques_image;
    }

    public void setQues_image(String ques_image) {
        this.ques_image = ques_image;
    }

    public String getQues_option1() {
        return ques_option1;
    }

    public void setQues_option1(String ques_option1) {
        this.ques_option1 = ques_option1;
    }

    public String getQues_option2() {
        return ques_option2;
    }

    public void setQues_option2(String ques_option2) {
        this.ques_option2 = ques_option2;
    }

    public String getQues_option3() {
        return ques_option3;
    }

    public void setQues_option3(String ques_option3) {
        this.ques_option3 = ques_option3;
    }

    public String getQues_option4() {
        return ques_option4;
    }

    public void setQues_option4(String ques_option4) {
        this.ques_option4 = ques_option4;
    }

    public String getQues_answer() {
        return ques_answer;
    }

    public void setQues_answer(String ques_answer) {
        this.ques_answer = ques_answer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
