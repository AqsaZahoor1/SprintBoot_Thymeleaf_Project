package com.java.springbootthymeleaf.services;

import java.util.List;

import com.java.springbootthymeleaf.models.Player;

public interface PlayerService {

	List<Player> getAllPlayers();

	void savePlayer(Player player);

	Player getPlayerById(Long id);
}
