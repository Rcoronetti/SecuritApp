package com.example.secureapp.security;

import com.example.secureapp.model.User;
import com.example.secureapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Usando o repositório para consultar o usuário.

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email); // Aqui você vai buscar o usuário no banco de dados.

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        userBuilder.password(user.getPassword()); // A senha será a que está armazenada no banco.
        userBuilder.roles(user.getRole()); // As roles do usuário (admin, user, etc.)

        return userBuilder.build();
    }
}
