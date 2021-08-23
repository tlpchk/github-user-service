package com.simple.rest.app.repository;

import com.simple.rest.app.model.LoginCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCounterRepository extends JpaRepository<LoginCounter, String> {
}
