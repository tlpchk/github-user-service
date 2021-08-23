package com.simple.rest.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "LOGIN_COUNTER")
@AllArgsConstructor
@NoArgsConstructor
public class LoginCounter {
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private long requestCount;

    public LoginCounter(String login) {
        this.login = login;
        this.requestCount = 0;
    }
}
