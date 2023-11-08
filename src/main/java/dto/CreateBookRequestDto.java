        package dto;

        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;
        import jakarta.validation.constraints.Pattern;
        import jakarta.validation.constraints.Positive;
        import jakarta.validation.constraints.Size;

        import java.math.BigDecimal;

        public record CreateBookRequestDto(
                @NotBlank
                String title,
                @NotBlank
                String author,
                @Pattern(regexp = "^\\d{3}-\\d{10}$", message = "ISBN must have the format 123-1234567890")
                String isbn,
                @NotNull
                @Positive
                BigDecimal price,
                @Size(max = 255)
                String description,
                @Size(max = 255)
                String coverImage
        ) {
        }
