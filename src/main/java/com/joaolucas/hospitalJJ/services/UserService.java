package com.joaolucas.hospitalJJ.services;

import com.joaolucas.hospitalJJ.exceptions.ResourceNotFoundException;
import com.joaolucas.hospitalJJ.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User não foi encontrado com email: " + email));
    }
}
