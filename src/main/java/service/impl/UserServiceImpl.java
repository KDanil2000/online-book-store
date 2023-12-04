package service.impl;

import dto.user.UserRegistrationRequestDto;
import dto.user.UserResponseDto;
import exceptions.RegistrationException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import model.Role;
import model.ShoppingCart;
import model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.RoleRepository;
import repository.UserRepository;
import service.UserService;

@Service
@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new RegistrationException("Registration failed");
        }
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRoles(getDefaultRoles());
        createUserShoppingCart(user);
        return userMapper.toDto(userRepository.save(user));
    }

    private Set<Role> getDefaultRoles() {
        return Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER));
    }

    private void createUserShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
    }
}
