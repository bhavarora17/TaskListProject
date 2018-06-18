package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@JsonSerialize
@JsonDeserialize
public class User extends Person {

    List<String> feedBacks;
    TreeSet<Task> taskList, finishedTask;

    public User(String name) {
        this.name = name;
        this.id = String. valueOf(nextValue());
        taskList = new TreeSet<>(new customComparator());
    }

    class customComparator implements Comparator<Task> {

        @Override
        public int compare(Task task1, Task task2) {

            if (task1.getStatusValue() == task2.getStatusValue())
                return task2.getRank() - task1.getRank();

            return  task1.getStatusValue() - task2.getStatusValue();
        }
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

    public TreeSet<Task> getTaskList() { return this.taskList; }

    public TreeSet<Task> getFinishTaskList() { return this.finishedTask; }

    public List<String> getFeedback() { return this.feedBacks; }


}
