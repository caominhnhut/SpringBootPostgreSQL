package com.sts.service.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sts.entity.RoleEntity;
import com.sts.entity.UserEntity;
import com.sts.exception.ValidationException;
import com.sts.model.user.User;
import com.sts.model.user.UserUpdateRequest;
import com.sts.repository.RoleRepository;
import com.sts.repository.UserRepository;
import com.sts.service.mapper.UserMapper;
import com.sts.util.ErrorCode;
import com.sts.util.enums.UserStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_GUEST";

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::fromUserEntity)
                .toList();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::fromUserEntity);
    }

    @Override
    public User updateUser(Long id, UserUpdateRequest userDetails) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    var updatedUser = UserEntity.builder()
                            .id(existingUser.getId())
                            .fullName(userDetails.getFullName())
                            .email(existingUser.getEmail())
                            .password(existingUser.getPassword())
                            .status(UserStatus.ACTIVE)
                            .build();
                    return userMapper.fromUserEntity(userRepository.save(updatedUser));
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber){
        Optional<UserEntity> userEntity = userRepository.findByPhoneNumber(phoneNumber);
        var user = userMapper.fromUserEntity(userEntity.orElse(null));
        return Optional.ofNullable(user);
    }

    @Override
    public Long register(User user){

        if (findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new ValidationException(ErrorCode.PHONE_NUMBER_ALREADY_EXISTS);
        }

        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        var userEntity = userMapper.toUserEntity(user);

        if(user.getRoleIds() != null && !user.getRoleIds().isEmpty()){
            Set<RoleEntity> roles = user.getRoleIds().stream()
                    .map(roleId -> roleRepository.findById(roleId)
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleId)))
                    .collect(Collectors.toSet());

            userEntity.setRoles(roles);
        } else {
            var defaultRole = roleRepository.findByName(DEFAULT_ROLE)
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            userEntity.setRoles(Set.of(defaultRole));
        }

        return userRepository.save(userEntity).getId();
    }

}
