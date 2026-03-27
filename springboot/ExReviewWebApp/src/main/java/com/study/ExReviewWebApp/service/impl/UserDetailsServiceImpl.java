package com.study.ExReviewWebApp.service.impl;

import com.study.ExReviewWebApp.entity.Users;
import com.study.ExReviewWebApp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public Users loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> optional = usersRepository.findByEmail(email);

        if(optional.isEmpty()){
            throw new UsernameNotFoundException("Not User");
        }

        Users users = optional.get();
        return users;
    }
}
