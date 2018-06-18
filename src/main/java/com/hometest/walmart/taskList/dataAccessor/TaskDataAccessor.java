package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.Task;
import com.hometest.walmart.taskList.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TaskDataAccessor {

    private CreateMongoDBCollection createMongoDBCollection;
    private UserDataAccessor userDataAccessor;

    @Autowired
    public void TaskDataAccessor(CreateMongoDBCollection createMongoDBCollection, UserDataAccessor userDataAccessor){
        this.createMongoDBCollection = createMongoDBCollection;
        this.userDataAccessor = userDataAccessor;
    }

    MongoCollection<Document> getTaskCollection() {

        createMongoDBCollection.createConnection();
        return createMongoDBCollection.getCollection("Task");

    }

    public Task getTaskDetails(String taskId) {

        List<User> userInfo = (List<User>) userDataAccessor.getUserInfo().values();

        for (User user : userInfo) {
            for (Task task : user.getTaskList()) {
                if (task.getID().equals(taskId))
                    return task;
            }
        }

        return null;

    }

    public void changeTaskStatus(String taskId, String userID, String status) {

        MongoCollection<Document> col = getTaskCollection();
        User user = userDataAccessor.getUserData(userID);

        for (Task task : user.getTaskList()) {
            if (task.getID().equals(taskId)) {
                task.setStatus(status);
                if (status.equals("FINISHED")) {
                    task.setCompletionTime(1);
                    user.getFinishTaskList().add(task);
                }
            }
        }

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

    public Task updateTask(String taskId, Task body){
        return null;
    }

    public void createTask(int priority, int estimatedTime, boolean isRecurring, String userId){

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
}
