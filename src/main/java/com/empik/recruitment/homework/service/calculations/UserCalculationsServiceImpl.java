package com.empik.recruitment.homework.service.calculations;

import com.empik.recruitment.homework.model.UserDTO;
import org.springframework.stereotype.Component;

@Component(value = "userCalculationServiceImpl")
public class UserCalculationsServiceImpl implements UserCalculationsService {
    @Override
    public double calculate(UserDTO userFullInfo) {
        return (6f / userFullInfo.getFollowersCount()) * (2 + userFullInfo.getPublicReposCount());
    }
}
