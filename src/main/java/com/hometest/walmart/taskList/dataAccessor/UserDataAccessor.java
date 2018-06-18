package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.Task;
import com.hometest.walmart.taskList.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class UserDataAccessor {

    private CreateMongoDBCollection createMongoDBCollection;
    private Map<String, User> userInfo;

    @Autowired
    public void UserDataAccessor(CreateMongoDBCollection createMongoDBCollection){
        this.createMongoDBCollection = createMongoDBCollection;
    }

    public Map<String, User> getUserInfo() { return userInfo; }

    public MongoCollection<Document> getUserCollection() {

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

    public void deleteUser(String userId){

        MongoCollection<Document> col = getUserCollection();
        col.deleteOne(new Document("_id", new ObjectId(userId)));
        userInfo.remove(userId);
        createMongoDBCollection.getMongoClient().close();

    }

    public void createNotes(String userId, String note){
        User user = getUserData(userId);
        List<String> notes = user.getNotes();
        notes.add(note);
    }

    public List<String> viewNotes(String userId) {

        User user = getUserData(userId);
        return user.getNotes();
    }

    public void giveFeedback(String userId, String feedback) {

        User user = getUserData(userId);
        List<String> feedBacks = user.getFeedback();
        feedBacks.add(feedback);

    }

    public List<String> viewFeedback(String userId) {

        User user = getUserData(userId);
        return user.getFeedback();

    }

    public User getUserData(String userId) {

        User user = null;
        MongoCollection<Document> col = getUserCollection();
        BasicDBObject query = new BasicDBObject("_id",new ObjectId(userId));
        FindIterable<Document> docs = col.find(query);

        if (docs != null) {

            for (Document doc : docs) {
                user = (User) doc.get("Data");
                String id = (String)doc.get("_id");
                userInfo.putIfAbsent(id, user);
            }

        }

        createMongoDBCollection.getMongoClient().close();
        return user;

    }

    public Map<String, User> getAllUsers(){

        userInfo = new HashMap<>();
        MongoCollection<Document> col = getUserCollection();
        try (MongoCursor<Document> cur = col.find().iterator()) {
            while (cur.hasNext()) {

                Document doc = cur.next();
                userInfo.putIfAbsent((String)doc.get("_id"), (User) doc.get("Data"));

            }
        }

        createMongoDBCollection.getMongoClient().close();
        return userInfo;
    }

}
