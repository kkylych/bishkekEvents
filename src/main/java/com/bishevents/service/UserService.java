package com.bishevents.service;
import com.bishevents.DTO.UserDTO;
import com.bishevents.Mapper.UserMapper;
import com.bishevents.entity.User_;
import com.bishevents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User_ user = UserMapper.toEntity(userDTO);
        User_ savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
