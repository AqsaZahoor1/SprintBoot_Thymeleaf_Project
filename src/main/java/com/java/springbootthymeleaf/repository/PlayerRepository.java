package com.java.springbootthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springbootthymeleaf.models.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {

}
