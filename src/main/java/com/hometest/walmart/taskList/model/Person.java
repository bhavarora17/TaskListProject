package com.hometest.walmart.taskList.model;

import java.util.ArrayList;
import java.util.List;


abstract class Person {

    int ID = 0;
    String name;
    List<String> notes = new ArrayList<>();

    Task createTask(int priority, int estimatedTime, boolean isRecurring) {

        Task task = new Task(priority, estimatedTime, isRecurring);
        return task;

    }

    abstract void updateNotes();
    abstract void checkAllTaskStatus();
    abstract void checkTaskStatusByID(int taskID);

    int getID() { return this.ID; }
    String getName() { return this.name; }

}


