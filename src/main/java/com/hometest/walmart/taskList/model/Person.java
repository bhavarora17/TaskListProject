package com.hometest.walmart.taskList.model;

import java.util.ArrayList;
import java.util.List;


abstract class Person extends Sequence{

    String id;
    String name;
    List<String> notes = new ArrayList<>();

    Task createTask(int priority, int estimatedTime, boolean isRecurring) {

        Task task = new Task(priority, estimatedTime, isRecurring);
        return task;

    }

    abstract void updateNotes();
    abstract void checkAllTaskStatus();

    public String getID() { return this.id; }
    public String getName() { return this.name; }
    public List<String> getNotes() {
        return notes;
    }

}


