package com.simple.rest.app.service.calculations;

import com.simple.rest.app.model.UserDTO;

public interface UserCalculationsService {
    double calculate(UserDTO userFullInfo);
}
