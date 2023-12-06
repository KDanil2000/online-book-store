package service.impl;

import dto.orderitem.OrderDto;
import dto.orderitem.OrderItemResponseDto;
import dto.orderitem.OrderRequestDto;
import dto.orderitem.OrderStatusUpdateDto;
import exceptions.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mapper.OrderItemMapper;
import mapper.OrderMapper;
import model.Order;
import model.OrderItem;
import model.ShoppingCart;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.OrderItemRepository;
import repository.OrderRepository;
import repository.ShoppingCartRepository;
import service.OrderService;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderDto> getAllById(Pageable pageable, Long id) {
        return orderRepository.findAllByUserId(pageable, id).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional
    @Override
    public OrderDto createOrder(Long id, OrderRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Shopping cart is empty")
        );
        Order order = orderMapper.toOrder(shoppingCart, requestDto);
        orderRepository.save(order);
        shoppingCart.clear();
        return orderMapper.toDto(order);
    }

    @Transactional
    @Override
    public OrderDto updateOrder(Long orderId,
                                OrderStatusUpdateDto updateDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException("Cannot find order by id " + orderId)
        );
        order.setStatus(updateDto.status());
        return orderMapper.toDto(order);
    }

    @Override
    public List<OrderItemResponseDto> getItemsByOrderId(Pageable pageable, Long orderId) {
        return orderItemRepository.findAllByOrderId(pageable, orderId).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getItemByOrderIdAndItemId(Long orderId,
                                                          Long itemId) {
        OrderItem orderItem = orderItemRepository.findByIdAndOrderId(itemId, orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cannot find item by id %d in order by id: %d".formatted(itemId, orderId))
                );
        return orderItemMapper.toDto(orderItem);
    }
}
