package com.example.userservice.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//TODO: JSONのスネークケース対応
//TODO: Lombok対応の検討
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {
    public String todoId;
    public String title;
}
