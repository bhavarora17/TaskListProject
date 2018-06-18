package com.hometest.walmart.taskList.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonSerialize
@JsonDeserialize
public class Manager extends Person{

    Map<String, User> userMap;

    public Manager(String name) {
        this.name = name;
        this.id = String. valueOf(nextValue());
        userMap = new HashMap<>();
    }

    @Override
    @JsonProperty
    public String getName() {
        return super.getName();
    }

    @Override
    @JsonProperty
    public String getID() {
        return super.getID();
    }

    @Override
    @JsonProperty
    public List<String> getNotes() { return super.getNotes(); }

    public Map<String, User> getAllUsers() { return this.userMap; }


}


