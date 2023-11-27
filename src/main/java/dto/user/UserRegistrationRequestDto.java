package dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(
        @NotBlank @Email
        String email,
        @NotNull @Size(min = 4, max = 32)
        @Pattern(
                regexp = "^[a-zA-Z0-9]{6,}$",
                message = "Password must contain at least one digit and letter"
        )
        String password,
        String verifyPassword,

        @NotBlank @Size(min = 2, max = 32)
        String firstName,

        @NotBlank @Size(min = 2, max = 32)
        String lastName,

        @NotBlank @Size(max = 128)
        String shippingAddress) {

}
