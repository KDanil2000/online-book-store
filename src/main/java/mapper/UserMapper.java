package mapper;

import dto.user.UserLoginRequestDto;
import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;
import model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = CartItemMapper.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toUser(UserRegistrationRequestDto requestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserLoginRequestDto updatedRequestDto, @MappingTarget User userToUpdate);
}
