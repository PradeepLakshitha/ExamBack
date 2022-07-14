package com.exam.examserver.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long quiz_id;

    private String quiz_title;

    @Column(length = 5000)
    private String quiz_description;

    private String quiz_maxMarks;

    private String quiz_numberOfQuestions;

    private boolean quiz_active = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    public Quiz() {
    }

    public Quiz(String quiz_title, String quiz_description, String quiz_maxMarks, String quiz_numberOfQuestions, boolean quiz_active) {
        this.quiz_title = quiz_title;
        this.quiz_description = quiz_description;
        this.quiz_maxMarks = quiz_maxMarks;
        this.quiz_numberOfQuestions = quiz_numberOfQuestions;
        this.quiz_active = quiz_active;
    }

    public Long getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(Long quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public void setQuiz_title(String quiz_title) {
        this.quiz_title = quiz_title;
    }

    public String getQuiz_description() {
        return quiz_description;
    }

    public void setQuiz_description(String quiz_description) {
        this.quiz_description = quiz_description;
    }

    public String getQuiz_maxMarks() {
        return quiz_maxMarks;
    }

    public void setQuiz_maxMarks(String quiz_maxMarks) {
        this.quiz_maxMarks = quiz_maxMarks;
    }

    public String getQuiz_numberOfQuestions() {
        return quiz_numberOfQuestions;
    }

    public void setQuiz_numberOfQuestions(String quiz_numberOfQuestions) {
        this.quiz_numberOfQuestions = quiz_numberOfQuestions;
    }

    public boolean isQuiz_active() {
        return quiz_active;
    }

    public void setQuiz_active(boolean quiz_active) {
        this.quiz_active = quiz_active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
