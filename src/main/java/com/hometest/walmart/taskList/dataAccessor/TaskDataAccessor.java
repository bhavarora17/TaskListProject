package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskDataAccessor {

    @Autowired
    public void TaskDataAccessor(){

    }

    public Task getTaskDetails(String id){
        return null;
    }

    public void deleteTask(String taskId){

    }

    public Task updateTask(String taskId, Task body){
        return null;
    }

    public void createTask(Task body, String userID){

    }
}
