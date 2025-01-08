package com.ansh.question_service.controller;


import com.ansh.question_service.model.Question;
import com.ansh.question_service.model.QuestionWrapper;
import com.ansh.question_service.model.Response;
import com.ansh.question_service.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.env.Environment;




import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController  {

    @Autowired
    QuestionService questionService;


    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getAllQuestionsByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("generate") //generate quiz
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
                              @RequestParam Integer numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    //return the question particular to the quiz(question id)-- Quiz service asking for Questions
    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        //System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    //getScore
    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }
}
