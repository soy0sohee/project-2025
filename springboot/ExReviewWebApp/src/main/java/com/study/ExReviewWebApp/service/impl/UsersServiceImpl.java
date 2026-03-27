package com.study.ExReviewWebApp.service.impl;

import com.study.ExReviewWebApp.dto.UserDto;
import com.study.ExReviewWebApp.dto.UserRequestDto;
import com.study.ExReviewWebApp.entity.Users;
import com.study.ExReviewWebApp.repository.UsersRepository;
import com.study.ExReviewWebApp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto createUser(UserRequestDto requestDto) {
        Users users = Users.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .userRole(requestDto.getUserRole())
                .build();
        Users entity = usersRepository.save(users);
        UserDto userDto = UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .userRole(entity.getUserRole())
                .build();
        return userDto;
    }

    @Override
    public UserDto findUser(String email) {
        return null;
    }

    @Override
    public UserDto findUserAndPassword(String email, String password) {
        Optional<Users> optional = usersRepository.findByEmail(email);
        if(optional.isEmpty()) {
            throw new IllegalArgumentException("해당 회원 없음");
        }
        Users users = optional.get();
        if(passwordEncoder.matches(password, users.getPassword()) == false) {
            throw new IllegalArgumentException("해당 회원 없음");
        }
        UserDto userDto = UserDto.builder()
                .email(users.getEmail())
                .password(users.getPassword())
                .userRole(users.getUserRole())
                .build();
        return userDto;
    }

    @Override
    public List<UserDto> findAll() {
        return List.of();
    }
}
