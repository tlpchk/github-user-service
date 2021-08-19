package com.empik.recruitment.homework.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String login;
    private String name;
    private String type; // Enum ?
    private String avatarUrl; // URL ?
    private String createdAt; // DateTime ?
    private String calculations;
}
