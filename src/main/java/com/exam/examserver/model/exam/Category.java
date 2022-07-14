package com.exam.examserver.model.exam;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cat_id;

    private String cat_title;

    private String cat_description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public Category(String cat_title, String cat_description) {
        this.cat_title = cat_title;
        this.cat_description = cat_description;
    }

    public Category() {
    }

    public Long getCat_id() {
        return cat_id;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_title() {
        return cat_title;
    }

    public void setCat_title(String cat_title) {
        this.cat_title = cat_title;
    }

    public String getCat_description() {
        return cat_description;
    }

    public void setCat_description(String cat_description) {
        this.cat_description = cat_description;
    }
}
