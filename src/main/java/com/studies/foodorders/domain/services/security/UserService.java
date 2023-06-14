package com.studies.foodorders.domain.services.security;

import com.studies.foodorders.domain.exceptions.BusinessException;
import com.studies.foodorders.domain.exceptions.UserNotFoundException;
import com.studies.foodorders.domain.models.security.User;
import com.studies.foodorders.domain.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public Optional<User> find(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Transactional
    public User save(User user) {
        userRepository.detach(user);
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent() && !existingUser.get().equals(user)) {
            throw new BusinessException(
                    String.format("There is already a user registered with the email %s", user.getEmail()));
        }

        return userRepository.save(user);
    }

    @Transactional
    public void updatePassword(Long id, String currentPassword, String newPassword) {
        User user = findIfExists(id);

        if (user.passwordDoesNotMatch(currentPassword)) {
            throw new BusinessException("Current password entered does not match the user's password.");
        }

        user.setPassword(newPassword);
    }

    public User findIfExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

}
