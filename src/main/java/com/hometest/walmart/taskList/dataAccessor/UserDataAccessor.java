package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.User;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDataAccessor {

    private CreateMongoDBCollection createMongoDBCollection;

    @Autowired
    public void UserDataAccessor(CreateMongoDBCollection createMongoDBCollection){
        this.createMongoDBCollection = createMongoDBCollection;
    }

    public void createNotes(String notes){
        return ;//
    }

    public String updateNotes(String userId, String notes){
        return null;
    }

    public String getNotes(String userId){
        return null;
    }

    public User updateUser(String userId){
        return null;// return updated user
    }

    public void deleteUser(String userId){
        //delete using userId
    }

    MongoCollection<Document> getUserCollection() {

        createMongoDBCollection.createConnection();
        return createMongoDBCollection.getCollection("People");

    }

    public void createUser(String userName) {

        MongoCollection<Document> col = getUserCollection();
        Document document = new Document();
        document.put("Type", "User");
        document.put("Data", new User(userName));
        col.insertOne(document);
        createMongoDBCollection.getMongoClient().close();

    }

    public User getUserData(int userId) {
        //mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));

        MongoCollection<Document> col = getUserCollection();

        User user = new User("Bhavya");
        user.ID = userId;

        return user;
    }

    public List<User> getAllUsers(){
        return null;
    }

    public void giveFeedback(){

    }

}
