package com.hometest.walmart.taskList.dataAccessors;

public class Task {

    private int ID;
    private int rank;
    private int priority;
    private int estimatedTime;
    private int completionTime;
    private String status;
    private boolean isRecurring;

    private static final int WEIGHTED_PRIORITY_CONSTANT = 10;
    private static final int WEIGHTED_TIME_CONSTANT = 5;

    public Task(int priority, int estimatedTime, boolean isRecurring) {

        this.ID = 0;
        this.priority = priority;
        this.estimatedTime = estimatedTime;
        this.isRecurring = isRecurring;
        this.status = "NOT STARTED";
    }

    public int getID() {
        return ID;
    }

    public int getRank() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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


