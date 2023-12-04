package dto.orderitem;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record OrderRequestDto(
        @NotNull @Length(max = 255)
        String shippingAddress) {
}
