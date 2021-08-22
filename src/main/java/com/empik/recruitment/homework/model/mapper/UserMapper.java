package com.empik.recruitment.homework.model.mapper;

import com.empik.recruitment.homework.model.User;
import com.empik.recruitment.homework.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDTOtoUser(UserDTO user);
}
