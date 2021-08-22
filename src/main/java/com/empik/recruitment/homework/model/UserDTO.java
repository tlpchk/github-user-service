package com.empik.recruitment.homework.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    @JsonProperty("followers")
    private int followersCount;
    @JsonProperty("public_repos")
    private int publicReposCount;
}
