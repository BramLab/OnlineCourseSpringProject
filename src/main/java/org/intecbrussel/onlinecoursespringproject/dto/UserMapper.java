package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.User;

public class UserMapper {

    public static UserResponseDto mapToUserDTO(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getRole()
        );
    }

//    public static User mapToUser(UserCreateUpdateDto userCreateUpdateDto) {
//        return new User(0,
//                userCreateUpdateDto.userName(),
//                userCreateUpdateDto.email(),
//                userCreateUpdateDto.role(),
//                null);
//    }

}
