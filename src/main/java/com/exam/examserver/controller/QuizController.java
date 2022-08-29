package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Category;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add quiz
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get all quizzes
    @GetMapping("/")
    public ResponseEntity<?> getAllQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get quiz by id
    @GetMapping("/{quizId}")
    public Quiz getQuizById(@PathVariable("quizId") Long quizId){
        return this.quizService.getQuiz(quizId);
    }

    //delete quiz
    @DeleteMapping("/{quizId}")
    public void deleteQuiz(@PathVariable("quizId") Long quizId){
        this.quizService.deleteQuiz(quizId);
    }

    //get quizzes of category
    @GetMapping("/category/{cat_id}")
    public List<Quiz> getQuizzessOfCategory(@PathVariable("cat_id") Long cat_id){
        Category category = new Category();
        category.setCat_id(cat_id);
        return this.quizService.getQuizzesOfCategory(category);
    }



}
