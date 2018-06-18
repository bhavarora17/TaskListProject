package com.hometest.walmart.taskList.model;

import java.util.ArrayList;
import java.util.List;


abstract class Person extends Sequence{

    public String ID;
    String name;

    public List<String> getNotes() {
        return notes;
    }

    List<String> notes = new ArrayList<>();

    Task createTask(int priority, int estimatedTime, boolean isRecurring) {

        Task task = new Task(priority, estimatedTime, isRecurring);
        return task;

    }

    abstract void updateNotes();
    abstract void checkAllTaskStatus();
    abstract void checkTaskStatusByID(int taskID);

    String getID() { return this.ID; }
    private void setID() { this.ID = String.valueOf(nextValue()); }
    String getName() { return this.name; }

}


