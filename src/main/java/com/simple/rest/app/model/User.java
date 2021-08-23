package com.simple.rest.app.model;

import lombok.Data;

@Data
public class User {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private double calculations;
}
