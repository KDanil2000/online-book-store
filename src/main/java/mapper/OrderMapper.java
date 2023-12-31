package mapper;

import dto.orderitem.OrderDto;
import dto.orderitem.OrderRequestDto;
import java.math.BigDecimal;
import java.util.Set;
import model.CartItem;
import model.Order;
import model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        implementationPackage = "<PACKAGE_NAME>.impl",
        uses = OrderItemMapper.class
)
public interface OrderMapper {
    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderItems", source = "shoppingCart.cartItems")
    @Mapping(target = "total", source = "shoppingCart.cartItems", qualifiedByName = "totalPrice")
    Order toOrder(ShoppingCart shoppingCart, OrderRequestDto orderRequestDto);

    @AfterMapping
    default void setOrderToItems(@MappingTarget Order order) {
        order.getOrderItems()
                .forEach(item -> item.setOrder(order));
    }

    @Named("totalPrice")
    default BigDecimal calculateTotalPrice(Set<CartItem> cartItems) {
        return cartItems.stream()
                .map(this::getTotalItemPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalItemPrice(CartItem cartItem) {
        BigDecimal quantity = BigDecimal.valueOf(cartItem.getQuantity());
        return cartItem.getBook().getPrice().multiply(quantity);
    }
}
