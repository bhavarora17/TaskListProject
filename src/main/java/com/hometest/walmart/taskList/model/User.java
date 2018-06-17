package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
@JsonDeserialize
public class User extends Person {

    List<String> feedBacks;
    List<Task> taskList, finishedTask;

    public User( String name) {
        this.name = name;
        taskList = new ArrayList<>();
    }

    @Override
    public void updateNotes() {

        notes.add("This is an example");

    }

    @Override
    public void checkAllTaskStatus() {

        for (Task task : taskList)
            System.out.println(task.getStatus());

    }

    @Override
    public void checkTaskStatusByID(int taskID) {

        for (Task task : taskList) {
            if (task.getID() == taskID)
                System.out.println(task.getStatus());
        }
    }

    void addTaskToTaskList(int priority, int estimatedTime, boolean isRecurring) {

        taskList.add(createTask(priority, estimatedTime, isRecurring));

    }


    public void changeTaskStatus(int taskID, String status) {

        for (Task task : taskList) {
            if (task.getID() == taskID) {
                task.setStatus(status);
                if (status.equals("FINISHED")) {
                    task.setCompletionTime(1);
                    this.finishedTask.add(task);
                }
            }
        }

    }


    void viewTaskList() {

        for (Task task : taskList)
            System.out.println(task.getID());

    }

    void viewFeedbacks() {

        for (String string : feedBacks)
            System.out.println(string);

    }

}
