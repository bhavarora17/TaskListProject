package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize
@JsonDeserialize
public class Manager extends Person{

    Map<String, User> userMap;

    public Manager(String name) {
        this.name = name;
        this.id = String. valueOf(nextValue());
        userMap = new HashMap<>();
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

    @Override
    @JsonProperty
    public List<String> getNotes() { return super.getNotes(); }

    @Override
    public void updateNotes() {

        notes.add("This is an example");

    }

    @Override
    public void checkAllTaskStatus() {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;

            for (Task task : taskList) {

                System.out.println(user.getID() + " " + user.getName() + " " +
                        task.getID() + " " + task.getPriority() + " " + task.getEstimatedTime() + " " +
                        task.getCompletionTime() + " " + task.getStatus());

            }
        }
    }


    void assignTaskToTheUser(int userID, int userName, int priority, int estimatedTime, boolean isRecurring) {

        for (Map.Entry<String, User> entry : userMap.entrySet()) {

            if (entry.getKey().equals(userID) && entry.getValue().getName().equals(userName))
                entry.getValue().taskList.add(createTask(priority, estimatedTime, isRecurring));

        }
    }

    void  viewUsers() {

        for (Map.Entry<String, User> entry : userMap.entrySet()) {

            System.out.println(entry.getKey() + " " + entry.getValue() );

        }

    }

    void viewTaskList() {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;
            for (Task task : taskList)
                System.out.println( task.getID()  + " " + user.getID() + " " + user.getName());

        }
    }

    void giveFeedBackToTheUser(int userID, int userName) {

        for (Map.Entry<String, User> entry : userMap.entrySet()) {

            if (entry.getKey().equals(userID) && entry.getValue().getName().equals(userName))
                entry.getValue().feedBacks.add("This is some feedback");

        }
    }

}


