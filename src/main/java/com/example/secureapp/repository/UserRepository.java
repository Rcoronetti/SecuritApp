package com.example.secureapp.repository;

import com.example.secureapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Método para buscar usuário por e-mail.
}
