package com.exam.examserver.controller;

import com.exam.examserver.model.exam.Question;
import com.exam.examserver.model.exam.Quiz;
import com.exam.examserver.service.QuestionService;
import com.exam.examserver.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //Add question
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }


    //update question
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    //get all questions
    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions(){
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    //get question by id
    @GetMapping("/{quesId}")
    public Question getQuestionById(@PathVariable("quesId") Long quesId){
        return this.questionService.getQuestion(quesId);
    }



    //get all question of quiz
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId){
//        Quiz quiz = new Quiz();
//        quiz.setQuiz_id(quizId);
//
//        Set<Question> questionsOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);
        Quiz quiz = this.quizService.getQuiz(quizId);
        Set<Question> questions = quiz.getQuestions();

        List list = new ArrayList<>(questions);
        if(list.size()>Integer.parseInt(quiz.getQuiz_numberOfQuestions())){
            list = list.subList(0,Integer.parseInt(quiz.getQuiz_numberOfQuestions()+1));

        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void deleteQuestionById(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }

}
