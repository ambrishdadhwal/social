package com.social.profile.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class TurnOnOff
{

	public void turnOn()
	{
		System.out.println("Load operating system.................");
	}

	public void turnOff()
	{
		System.out.println("Close all programs......................");
	}

	@PostConstruct
	public void turnOn1()
	{
		System.out.println("alternate Load operating system.................");
	}

	@PreDestroy
	public void turnOff1()
	{
		System.out.println("alternate Close all programs......................");
	}
}
