package com.empik.recruitment.homework.repository;

import com.empik.recruitment.homework.model.LoginCounter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginCounterRepository extends JpaRepository<LoginCounter, String> {
}
