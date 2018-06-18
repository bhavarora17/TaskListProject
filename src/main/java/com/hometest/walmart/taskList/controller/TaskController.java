package com.hometest.walmart.taskList.controller;

import com.hometest.walmart.taskList.dataAccessor.TaskDataAccessor;
import com.hometest.walmart.taskList.dataAccessor.UserDataAccessor;
import com.hometest.walmart.taskList.model.Task;
import com.hometest.walmart.taskList.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private UserDataAccessor userDataAccessor;
    private TaskDataAccessor taskDataAccessor;

    @Autowired
    TaskController(UserDataAccessor userDataAccessor, TaskDataAccessor taskDataAccessor){
        this.userDataAccessor = userDataAccessor;
        this.taskDataAccessor = taskDataAccessor;
    }

    @GetMapping("manage/getUserList")
    public ResponseEntity<Object> getListAll() {
        List<User> userList = userDataAccessor.getAllUsers();
        return new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping("/manage/giveFeedback")
    public ResponseEntity<Object> provideFeedback(@RequestBody String body, @PathVariable("feedback") final String feedback) {
        userDataAccessor.giveFeedback();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<Object> getTaskListUser(@RequestParam(value = "userId", required = true) final int userId) {
        User user = userDataAccessor.getUserData(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("user/create")
    public ResponseEntity createUser(@RequestBody User user) {
        userDataAccessor.createUser(user.getName());
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("user/delete")
    public ResponseEntity deleteUserByManager(@RequestParam(value = "userId", required = true) final String userId) {
        userDataAccessor.deleteUser(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("user/update")
    public ResponseEntity<Object> updateUserByManager(@RequestParam(value = "userId", required = true) final String userId, @RequestBody User body) {
        User user = userDataAccessor.updateUser(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PatchMapping("task/update/{taskid}")
    public ResponseEntity updateTask(@RequestBody Task body, @PathVariable("taskId") final String taskId) {

        Task updatedTask = taskDataAccessor.updateTask(taskId, body);
        return new ResponseEntity(updatedTask, HttpStatus.OK);
    }

    @PostMapping("task/create/{userId}")
    public ResponseEntity createTask(@RequestBody Task body, @PathVariable("userId") final String userId) {
        taskDataAccessor.createTask(body, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("task/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") final String taskId) {
        taskDataAccessor.deleteTask(taskId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("notes/create")
    public ResponseEntity createNotes(@RequestBody String body) {
        userDataAccessor.createNotes(body);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("notes/update/{userId}")
    public ResponseEntity updateNotes(@RequestBody String body, @PathVariable("userId") final String userId) {
        String updatedNotes = userDataAccessor.updateNotes(userId, body);
        return new ResponseEntity(updatedNotes, HttpStatus.OK);
    }

    @GetMapping("/notes/{notesId}")
    public ResponseEntity getNotes(@PathVariable("notesId") String notesId) {
        String notes = userDataAccessor.getNotes(notesId);
        return new ResponseEntity(HttpStatus.OK);
    }
}