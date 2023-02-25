package com.social.profile.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.social.profile.notification.ProfileNotificationDetails;
import com.social.profile.notification.ProfileNotificationService;

@Component
public class AuthenticationEvents
{

	@Autowired
	ProfileNotificationService emailService;

	@Value("${notificaion.email}")
	String userName;

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent success)
	{
		ProfileNotificationDetails emailDetails = ProfileNotificationDetails.builder()
			.recipient(userName)
			.subject("Login Succeed")
			.msgBody("Login Succeed for application.")
			.build();

		emailService.sendSimpleMail(emailDetails);
		System.out.println("###...success event is published...###" + success.getTimestamp());
	}

	@EventListener
	public void onFailure(AbstractAuthenticationFailureEvent failures)
	{
		ProfileNotificationDetails emailDetails = ProfileNotificationDetails.builder()
			.recipient("ammydev321@gmail.com")
			.subject("Login Failure")
			.msgBody("Login Failed for application. Try with valid credentials.")
			.build();

		emailService.sendSimpleMail(emailDetails);
		System.out.println("###...onFailure event is published...###" + failures.getTimestamp());
	}
}
