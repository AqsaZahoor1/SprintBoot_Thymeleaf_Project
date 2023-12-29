package com.java.springbootthymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.springbootthymeleaf.models.Player;
import com.java.springbootthymeleaf.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	private PlayerRepository playerRepository;

	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Override
	public List<Player> getAllPlayers() {

		Sort sort = Sort.by(Sort.Direction.DESC, "points");

		// Use the findAll method with sorting
		return playerRepository.findAll(sort);
	}

	@Override
	public void savePlayer(Player player) {
		playerRepository.save(player);
	}

	@Override
	public Player getPlayerById(Long id) {
		Optional<Player> optional = playerRepository.findById(id);
		Player player = null;
		if (optional.isPresent()) {
			player = optional.get();
		} else {
			throw new RuntimeException("Player not found for:: " + id);
		}
		return player;
	}

}
