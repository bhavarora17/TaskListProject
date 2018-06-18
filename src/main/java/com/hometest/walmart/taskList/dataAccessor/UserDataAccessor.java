package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDataAccessor {

    @Autowired
    public void UserDataAccessor(){

    }

    public User updateUser(String userId){
        return null;// return updated user
    }

    public void deleteUser(String userId){
        //delete using userId
    }

    public void createUser(){

    }

    public User getUserData(String userId) {
        //mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));
        User user = new User("Bhavya");
        user.ID = 123;

        return user;
    }

    public List<User> getAllUsers(){
        return null;
    }

    public void giveFeedback(){

    }

}
