package com.sts.service.user;

import com.sts.entity.UserEntity;
import com.sts.util.enums.UserStatus;
import com.sts.exception.ValidationException;
import com.sts.model.user.User;
import com.sts.model.user.UserUpdateRequest;
import com.sts.repository.UserRepository;
import com.sts.service.mapper.UserMapper;
import com.sts.util.ErrorCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

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
        return userRepository.save(userEntity).getId();
    }

}
