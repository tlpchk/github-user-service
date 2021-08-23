package com.simple.rest.app.model.mapper;

import com.simple.rest.app.model.User;
import com.simple.rest.app.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOtoUser(UserDTO user);
}
