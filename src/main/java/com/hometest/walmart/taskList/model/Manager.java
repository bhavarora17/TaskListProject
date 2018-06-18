package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize
@JsonDeserialize
public class Manager extends Person{

    Map<Integer, User> userMap;

    public Manager(String name) {
        this.name = name;
        userMap = new HashMap<>();
    }

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

    @Override
    public void checkTaskStatusByID(int taskID) {

        for (User user : userMap.values()) {

            List<Task> taskList = user.taskList;

            for (Task task : taskList) {

                if (task.getID() == taskID) {
                    System.out.println(user.getName() + " " + user.getID() + " "
                            + task.getID() + " " + task.getPriority() + " " + task.getEstimatedTime()
                            + " " + task.getCompletionTime() + " " + task.getStatus());
                }
            }
        }
    }

    void createUser(String name) {

        User user = new User(name);
        userMap.put(user.getID(), user);

    }

    void deleteUser(int userID, String name) {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            if (entry.getKey() == userID && entry.getValue().getName().equals(name))
                userMap.remove(entry.getKey());

        }

    }

    void assignTaskToTheUser(int userID, int userName, int priority, int estimatedTime, boolean isRecurring) {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            if (entry.getKey() == userID && entry.getValue().getName().equals(userName))
                entry.getValue().taskList.add(createTask(priority, estimatedTime, isRecurring));

        }
    }

    void  viewUsers() {

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

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

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {

            if (entry.getKey() == userID && entry.getValue().getName().equals(userName))
                entry.getValue().feedBacks.add("This is some feedback");

        }
    }

}


