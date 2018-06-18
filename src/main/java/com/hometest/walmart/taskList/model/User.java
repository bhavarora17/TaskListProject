package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize
@JsonDeserialize
public class User extends Person {

    List<String> feedBacks, Notes;
    List<Task> taskList, finishedTask;

    public User(String name) {
        this.name = name;
        this.id = String. valueOf(nextValue());
        taskList = new ArrayList<>();
    }

    @Override
    @JsonProperty
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty
    public String getID() {
        return super.getID();
    }

    public List<Task> getTaskList() { return this.taskList; }

    public List<Task> getFinishTaskList() { return this.finishedTask; }

    @Override
    @JsonProperty
    public List<String> getNotes() { return super.getNotes(); }

    public List<String> getFeedbacks() { return this.feedBacks; }

    @Override
    public void updateNotes() {

        notes.add("This is an example");

    }

    @Override
    public void checkAllTaskStatus() {

        for (Task task : taskList)
            System.out.println(task.getStatus());

    }

    void addTaskToTaskList(int priority, int estimatedTime, boolean isRecurring) {

        taskList.add(createTask(priority, estimatedTime, isRecurring));

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
