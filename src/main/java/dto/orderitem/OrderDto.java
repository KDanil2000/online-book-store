package dto.orderitem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import model.Order;

public record OrderDto(
        Long id,
        Long userId,
        Set<OrderItemResponseDto> orderItems,
        LocalDateTime orderDate,
        BigDecimal total,
        Order.Status status
) {
}
