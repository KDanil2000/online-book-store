package controller;

import dto.user.UserLoginRequestDto;
import dto.user.UserLoginResponseDto;
import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;
import exceptions.RegistrationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.AuthenticationService;
import service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Book store Authentication",
        description = "Endpoints for authentication and registration")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Registration",
            description = "Register new user")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login",
            description = "Login user by email and password")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
