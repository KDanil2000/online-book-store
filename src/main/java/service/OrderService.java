package service;

import dto.orderitem.OrderDto;
import dto.orderitem.OrderItemResponseDto;
import dto.orderitem.OrderRequestDto;
import dto.orderitem.OrderStatusUpdateDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderDto> getAllById(Pageable pageable, Long id);

    OrderDto createOrder(Long id, OrderRequestDto requestDto);

    OrderDto updateOrder(Long orderId, OrderStatusUpdateDto updateDto);

    List<OrderItemResponseDto> getItemsByOrderId(Pageable pageable, Long orderId);

    OrderItemResponseDto getItemByOrderIdAndItemId(Long orderId, Long itemId);
}
