package com.anchtun.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class NameService {

	private String[] names = { "Mohamed", "Aymen", "Anchtun" };
	private Random random = new Random();

	public String getName() {
		return names[random.nextInt(names.length)];
	}
}
