package com.java.springbootthymeleaf.config;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.java.springbootthymeleaf.models.Player;
import com.java.springbootthymeleaf.services.PlayerServiceImpl;

@EnableScheduling
@Configuration
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	PlayerServiceImpl playerService;

	@Scheduled(fixedDelay = 3000)
	public void sendAdhocMessages() {

		Random random = new Random();

		// Generate a random integer between 1 and 10 (inclusive)
		Long randomNumber = (long) (random.nextInt(10) + 1);

		Player randomPlayer = playerService.getPlayerById(randomNumber);

		randomPlayer.setPoints(randomPlayer.getPoints() + 1);

		playerService.savePlayer(randomPlayer);

		template.convertAndSend("/topic/user", playerService.getAllPlayers());
	}
}