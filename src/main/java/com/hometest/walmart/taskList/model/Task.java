package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize
@JsonDeserialize
public class Task extends Sequence {

    class Status {

        private String name;

        Status(String name) {
            this.name = name;
        }

         public int getValue() {
            if (this.name.equalsIgnoreCase("PENDING"))
                return 1;
            else if (this.name.equalsIgnoreCase("PROCESSING"))
                return 2;
            else if (this.name.equalsIgnoreCase("FINISHED"))
                return 3;
            return 0;
         }
    }

    private String ID;
    private int rank = 0;
    private int priority;
    private int estimatedTime;
    private int completionTime;
    private Status status;
    private boolean isRecurring;
    private Map<String, Integer> statusMap;
    private static final int WEIGHTED_PRIORITY_CONSTANT = 10;
    private static final int WEIGHTED_TIME_CONSTANT = 5;

    public Task(int priority, int estimatedTime, boolean isRecurring) {

        this.ID = String.valueOf(nextValue());
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.isRecurring = isRecurring;
        this.status = new Status("PENDING");

    }

    public String getID() {
        return this.ID;
    }

    public int getRank() {

        if (rank == 0)
            calculateRankOfTheTask();

        return rank;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public int getStatusValue() {
        return status.getValue();
    }

    public String getStatus() {
        return status.name;
    }

    public void setStatus(String status) {
        this.status.name = status;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    void calculateRankOfTheTask() {

        int weightedPriority = this.priority * WEIGHTED_PRIORITY_CONSTANT;
        int weightedTime = this.estimatedTime * WEIGHTED_TIME_CONSTANT;
        this.rank = Math.round((weightedPriority/weightedTime) * 100);

    }

}


