package com.hometest.walmart.taskList.dataAccessor;

import com.hometest.walmart.taskList.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class UserDataAccessor {

    @Autowired
    public void UserDataAccessor(){

    }

    public User getUserData(String userId) {
        //mapper.setVisibilityChecker(mapper.getVisibilityChecker().withFieldVisibility(Visibility.ANY));
        User user = new User("Bhavya");
        user.ID = 123;

        return user;
    }

}
