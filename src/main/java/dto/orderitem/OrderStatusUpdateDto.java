package dto.orderitem;

import jakarta.validation.constraints.NotNull;
import model.Order;

public record OrderStatusUpdateDto(
        @NotNull
        Order.Status status
) {
}
