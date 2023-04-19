package com.company.service;

import com.company.dto.UserDTO;
import com.company.entity.UserEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String update(String username, UserDTO dto) {
        UserEntity entity = getUser(username);
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(entity);
        return "Successfully updated";
    }

    public UserDTO findUser(String username) {
        UserEntity user = getUser(username);
        UserDTO dto = new UserDTO();
        dto.setName(user.getName());
        dto.setUsername(dto.getUsername());
        return dto;
    }

    public String delete(String username) {
        userRepository.deleteById(getUser(username).getId());
        return "Deleted";
    }

    public UserDTO addUser(UserDTO dto) {
        Optional<UserEntity> opt = userRepository.findByUsername(dto.getUsername());
        if (opt.isPresent()) {
            throw new BadRequestException("This username is busy. Please try again with another username!");
        }
        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);

        return getDTO(user);
    }
    public UserEntity getEntity(String username) {
        return getUser(username);
    }

    private UserDTO getDTO(UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    private UserEntity getUser(String username) {
        Optional<UserEntity> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("User not found!");
        }
        return optional.get();
    }
}
