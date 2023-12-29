package com.java.springbootthymeleaf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.java.springbootthymeleaf.models.Player;
import com.java.springbootthymeleaf.services.PlayerServiceImpl;

@Controller
public class WebSocketController {

	@Autowired
	PlayerServiceImpl playerServiceImpl;

	@MessageMapping("/user")
	@SendTo("/topic/user")
	public List<Player> getUser() {

		return playerServiceImpl.getAllPlayers();
	}
}
