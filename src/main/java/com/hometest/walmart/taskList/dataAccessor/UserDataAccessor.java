package com.hometest.walmart.taskList.dataAccessor;

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

import static com.mongodb.client.model.Filters.eq;


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

    public User getUserData(String userId) {
        //mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));

        User user = null;

        MongoCollection<Document> col = getUserCollection();

        BasicDBObject query = new BasicDBObject("_id",new ObjectId(userId));
        FindIterable<Document> docs = col.find(query);
        if (docs != null) {

            for (Document doc : docs) {
                user = (User) doc.get("Data");
            }

        }

        createMongoDBCollection.getMongoClient().close();

        return user;
    }

    public Map<String, User> getAllUsers(){

        Map<String, User> userInfo = new HashMap<>();
        MongoCollection<Document> col = getUserCollection();
        try (MongoCursor<Document> cur = col.find().iterator()) {
            while (cur.hasNext()) {

                Document doc = cur.next();
                userInfo.put((String)doc.get("_id"), (User)doc.get("Data"));

            }
        }

        createMongoDBCollection.getMongoClient().close();

        return userInfo;
    }

    public void giveFeedback(){

    }

}
