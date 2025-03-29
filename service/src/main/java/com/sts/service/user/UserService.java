package com.sts.service.user;

import com.sts.model.user.User;
import com.sts.model.user.UserUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(Long id, UserUpdateRequest userDetails);

    void deleteUser(Long id);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Long register(User user);

}
