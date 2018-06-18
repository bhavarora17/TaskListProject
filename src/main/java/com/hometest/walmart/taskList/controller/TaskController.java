package com.hometest.walmart.taskList.controller;

import com.hometest.walmart.taskList.dataAccessor.TaskDataAccessor;
import com.hometest.walmart.taskList.dataAccessor.UserDataAccessor;
import com.hometest.walmart.taskList.model.Task;
import com.hometest.walmart.taskList.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TaskController {

    private UserDataAccessor userDataAccessor;
    private TaskDataAccessor taskDataAccessor;

    @Autowired
    TaskController(UserDataAccessor userDataAccessor, TaskDataAccessor taskDataAccessor){
        this.userDataAccessor = userDataAccessor;
        this.taskDataAccessor = taskDataAccessor;
    }

    @GetMapping("/viewManagerActivities")
    public String passParametersWithModelMap() {
        return "manager";
    }

    @GetMapping("manager/viewUser")
    public ResponseEntity<Object> viewUserByManager(@RequestParam(value = "userId", required = true) final String userId) {
        User user = userDataAccessor.getUserData(userId);
        return new ResponseEntity(user, HttpStatus.OK);
    }
    @GetMapping("manager/getUserList")
    public ModelAndView getListAll(@ModelAttribute User user) {
        Map<String, User> userList = userDataAccessor.getAllUsers();
        ModelAndView model = new ModelAndView("manager.jsp");
        model.addObject("usersList", userList);
        return model;  //new ResponseEntity(userList, HttpStatus.OK);
    }

    @PostMapping("/manager/giveFeedback/{userId}")
    public ResponseEntity<Object> provideFeedback(@RequestParam(value = "userId", required = true) final String userId, @PathVariable("feedback") final String feedback) {
        userDataAccessor.giveFeedback(userId, feedback);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("user")
    public ResponseEntity<Object> getTaskListUser(@RequestParam(value = "userId", required = true) final String userId) {
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

    @GetMapping("/user/viewFeedback")
    public ResponseEntity<Object> viewFeedbackByUser(@RequestParam(value = "userId", required = true) final String userId) {
        userDataAccessor.viewFeedback(userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("task/update/{taskId}")
    public ResponseEntity updateTask(@RequestBody Task body, @PathVariable("taskId") final String taskId) {

        Task updatedTask = taskDataAccessor.updateTask(taskId, body);
        return new ResponseEntity(updatedTask, HttpStatus.OK);
    }

    @PostMapping("task/create/{userId}")
    public ResponseEntity createTask(@RequestBody Task body, @PathVariable("userId") final String userId) {
        taskDataAccessor.createTask(body.getPriority(), body.getEstimatedTime(), body.isRecurring(), userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("task/delete/{taskId}")
    public ResponseEntity deleteTask(@PathVariable("taskId") final String taskId, @PathVariable("userId") final String userId) {
        taskDataAccessor.deleteTask(taskId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("user/createNote")
    public ResponseEntity createNotes(@RequestParam(value = "userId", required = true) final String userId, @PathVariable("feedback") final String notes) {
        userDataAccessor.createNotes(userId, notes);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("notes/update/{userId}")
    public ResponseEntity updateNotes(@RequestBody String body, @PathVariable("userId") final String userId) {
        String updatedNotes = userDataAccessor.updateNotes(userId, body);
        return new ResponseEntity(updatedNotes, HttpStatus.OK);
    }

    @GetMapping("/notes/{notesId}")
    public ResponseEntity getNotes(@PathVariable("notesId") String notesId) {
        List<String> notes = userDataAccessor.getNotes(notesId);
        return new ResponseEntity(HttpStatus.OK);
    }
}