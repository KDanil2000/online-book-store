package controller;

import dto.orderitem.OrderDto;
import dto.orderitem.OrderItemResponseDto;
import dto.orderitem.OrderRequestDto;
import dto.orderitem.OrderStatusUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

@Tag(name = "Order management", description = "Endpoints for managing orders")
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get orders",
            description = "Receive user's order history")
    public List<OrderDto> getOrders(Authentication authentication,
                                    @PageableDefault Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllById(pageable, user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Place order",
            description = "Allows user to place an order")
    public OrderDto placeOrder(Authentication authentication,
                               @RequestBody @Valid OrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.createOrder(user.getId(), requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update order",
            description = "Update the status of an existing order")
    public OrderDto updateOrder(@RequestBody @Valid OrderStatusUpdateDto updateDto,
                                @PathVariable @Positive Long id) {
        return orderService.updateOrder(id, updateDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}/items")
    @Operation(summary = "Get order items by order id",
            description = "Receive order items by order id")
    public List<OrderItemResponseDto> getItemsByOrderId(@PageableDefault Pageable pageable,
                                                        @PathVariable @Positive Long id) {
        return orderService.getItemsByOrderId(pageable, id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item by order id and item id",
            description = "Receive order item by order id and item id")
    public OrderItemResponseDto getItemByOrderAndItemId(@PathVariable @Positive Long orderId,
                                                        @PathVariable @Positive Long itemId) {
        return orderService.getItemByOrderIdAndItemId(orderId, itemId);
    }
}
