package service;

import dto.shoppingcart.CreateCartItemRequestDto;
import dto.shoppingcart.ShoppingCartDto;
import dto.shoppingcart.UpdateCartItemRequestDto;

public interface ShoppingCartService {
    ShoppingCartDto findById(Long id);

    ShoppingCartDto addToCart(Long id, CreateCartItemRequestDto requestDto);

    ShoppingCartDto update(Long userId, Long cartItemId, UpdateCartItemRequestDto quantityDto);

    ShoppingCartDto removeCartItem(Long userId, Long cartItemId);
}
