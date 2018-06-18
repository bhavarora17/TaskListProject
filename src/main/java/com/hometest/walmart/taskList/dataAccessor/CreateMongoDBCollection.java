package com.hometest.walmart.taskList.dataAccessor;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class CreateMongoDBCollection {

    private MongoClient mongoClient;
    private MongoDatabase taskDB;

    MongoClient getMongoClient() { return mongoClient; }
    void createConnection () {

        try {

            mongoClient = new MongoClient("localhost", 27017);
            taskDB = mongoClient.getDatabase("ReplenisherTaskList");
            System.out.println("Your connection to DB is ready for Use: " + taskDB);

        } catch (Exception e) {

            System.out.println(e.getClass().getName() + " : " + e.getMessage());

        }
    }

    void createCollection (String collectionName) {

        taskDB.createCollection(collectionName);
        System.out.println("Collection created successfully");

    }

    MongoCollection<Document> getCollection (String collectionName) {

        if (!doesCollectionExist(taskDB, collectionName))
            createCollection(collectionName);

        return taskDB.getCollection(collectionName);
    }

     boolean doesCollectionExist(MongoDatabase taskDB, String collectionName) {

        MongoCollection<Document> col = taskDB.getCollection(collectionName);
        return (col.count() > 0) ? true : false;

    }
}
