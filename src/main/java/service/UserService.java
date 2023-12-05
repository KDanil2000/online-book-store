package service;

import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;
import exceptions.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto request) throws RegistrationException;
}
