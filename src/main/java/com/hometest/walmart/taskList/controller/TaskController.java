package com.hometest.walmart.taskList.controller;

import com.hometest.walmart.taskList.dataAccessors.Task;
import com.hometest.walmart.taskList.dataAccessors.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @GetMapping("manager")
    public ResponseEntity getListAll(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manage/feedback")
    public ResponseEntity provideFeedback(@RequestBody String body, @PathVariable("feedback") final String feedback){
        return new ResponseEntity(HttpStatus.OK);
    }


    @GetMapping("user")
    public ResponseEntity getTaskListUser(@RequestParam(value = "userId", required = true) final String userId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("user/create")
    public ResponseEntity createUser(@RequestBody User user){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("user/delete")
    public ResponseEntity deleteUserByManager(@RequestParam(value = "userId", required = true) final String userId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("user/update")
    public ResponseEntity updateUserByManager(@RequestParam(value = "userId", required = true) final String userId, @RequestBody User body){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("task/update/{taskid}")
    public ResponseEntity updateTask(@RequestBody Task body, @PathVariable("taskId") final String taskId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("task/create/{userId}")
    public ResponseEntity createTask(@RequestBody Task body, @PathVariable("userId") final String userId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("task/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") final String taskId){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("notes/create")
    public ResponseEntity createNotes(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("notes/update/{notesId}")
    public ResponseEntity updateNotes(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/notes/{notesId}")
    public ResponseEntity getNotes(@RequestBody String body){
        return new ResponseEntity(HttpStatus.OK);
    }

}