package com.pos.posspringbackend.token.repository;

import com.pos.posspringbackend.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token t inner join User u on t.user.id = u.id WHERE u.id = :id AND t.expired = false OR t.revoked = false")
    List<Token> findAllValidTokensByUserId(Long id);
    Optional<Token> findByToken(String token);
}
