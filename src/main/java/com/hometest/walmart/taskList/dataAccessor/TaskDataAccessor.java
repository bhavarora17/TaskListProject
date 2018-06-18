package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.Sequence;
import com.hometest.walmart.taskList.model.Task;
import com.hometest.walmart.taskList.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskDataAccessor {

    private CreateMongoDBCollection createMongoDBCollection;
    private UserDataAccessor userDataAccessor;
    private Sequence sequence;

    @Autowired
    public void TaskDataAccessor(CreateMongoDBCollection createMongoDBCollection,
                                                    Sequence sequence, UserDataAccessor userDataAccessor) {

        this.createMongoDBCollection = createMongoDBCollection;
        this.userDataAccessor = userDataAccessor;
        this.sequence = sequence;
    }

    public MongoCollection<Document> getTaskCollection() {

        createMongoDBCollection.createConnection();
        return createMongoDBCollection.getCollection("Task");

    }

    public void createTask(int priority, int estimatedTime, boolean isRecurring, String userId) {

        MongoCollection<Document> col = getTaskCollection();

        Document document = new Document();
        Task task = new Task(priority, estimatedTime, isRecurring);
        document.put("Type", "Task");
        document.put("User", userId);
        document.put("Data", task);
        col.insertOne(document);
        User user = userDataAccessor.getUserData(userId);
        user.getTaskList().add(task);

        createMongoDBCollection.getMongoClient().close();

    }

    public void deleteTask(String taskId, String userID){

        MongoCollection<Document> col = getTaskCollection();
        User user = userDataAccessor.getUserData(userID);
        col.deleteOne(new Document("_id", new ObjectId(taskId)));

        for (Task task : user.getTaskList()) {

            if (task.getID().equals(taskId))
                user.getTaskList().remove(task);

        }

        createMongoDBCollection.getMongoClient().close();

    }

    public List<String> getTaskDetails(String taskId) {

        List<User> userInfo = (List<User>) userDataAccessor.getUserInfo().values();
        List<String> taskDetails = new ArrayList<>();

        for (User user : userInfo) {
            for (Task task : user.getTaskList()) {
                if (task.getID().equals(taskId)) {
                    taskDetails.add(task.getID());
                    taskDetails.add(task.getStatus());
                    taskDetails.add(String.valueOf(task.getPriority()));
                    return taskDetails;
                }
            }
        }

        return taskDetails;

    }

    public Map<String, List<String>> getAllTaskDetails() {

        List<User> userInfo = (List<User>) userDataAccessor.getUserInfo().values();
        Map<String, List<String>> allTaskDetails = new HashMap<>();
        for (User user : userInfo) {

            List<String> taskDetails = new ArrayList<>();

            for (Task task : user.getTaskList()) {
                 taskDetails.add(task.getID());
                 taskDetails.add(task.getStatus());
                 taskDetails.add(String.valueOf(task.getPriority()));
            }

            allTaskDetails.put(user.getID(), taskDetails);
        }

        return allTaskDetails;

    }

    public void changeTaskStatus(String taskId, String userID, String status) {

        MongoCollection<Document> col = getTaskCollection();
        User user = userDataAccessor.getUserData(userID);

        for (Task task : user.getTaskList()) {
            if (task.getID().equals(taskId)) {
                task.setStatus(status);
                if (status.equalsIgnoreCase("FINISHED")) {
                    task.setCompletionTime(sequence.nextValue());
                    user.getFinishTaskList().add(task);
                }
            }
        }

        createMongoDBCollection.getMongoClient().close();
    }



    public Map<String, TreeSet<Task>> viewTaskForAllUsers(String taskId, Task body) {

        Map<String, TreeSet<Task>> taskInfo = new HashMap<>();
        List<User> userInfo = (List<User>) userDataAccessor.getUserInfo().values();

        for (User user : userInfo)
            taskInfo.put(user.getID(), user.getTaskList());

        return taskInfo;


    }

}
