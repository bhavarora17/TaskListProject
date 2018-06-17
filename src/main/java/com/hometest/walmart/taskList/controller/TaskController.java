package com.hometest.walmart.taskList.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @GetMapping("manager")
    public ResponseEntity getListAll(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity getTaskListUser(@RequestParam(value = "userId", required = true) final String userId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("user/create")
    public ResponseEntity createUser(@RequestBody String user){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("user/delete")
    public ResponseEntity deleteUserByManager(@RequestParam(value = "userId", required = true) final String userId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("user/update")
    public ResponseEntity updateUserByManager(@RequestParam(value = "userId", required = true) final String userId, @RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }


    @PatchMapping("/update/task/{taskid}")
    public ResponseEntity updateTask(@RequestBody String body, @PathVariable("taskId") final String taskId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create/task/{user}")
    public ResponseEntity createTask(@RequestBody String body, @PathVariable("user") final String user){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/task/{taskId}")
    public ResponseEntity deleteMapping(@PathVariable("taskId") final Integer taskId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manage/feedback")
    public ResponseEntity provideFeedback(@RequestBody String body, @PathVariable("feedback") final String feedback){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/create/notes")
    public ResponseEntity provideFeedback(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/update/notes/{notesId}")
    public ResponseEntity updateNotes(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/notes/{notesId}")
    public ResponseEntity getNotes(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

}