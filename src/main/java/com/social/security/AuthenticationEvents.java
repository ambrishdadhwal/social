package com.social.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import com.social.notification.SocialNotificationDetails;
import com.social.notification.SocialNotificationService;

@Component
public class AuthenticationEvents
{

	@Autowired
	SocialNotificationService emailService;

	@Value("${spring.mail.username}")
	String userName;

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent success)
	{
		SocialNotificationDetails emailDetails = SocialNotificationDetails.builder()
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
		SocialNotificationDetails emailDetails = SocialNotificationDetails.builder()
			.recipient(userName)
			.subject("Login Failure")
			.msgBody("Login Failed for application. Try with valid credentials.")
			.build();

		emailService.sendSimpleMail(emailDetails);
		System.out.println("###...onFailure event is published...###" + failures.getTimestamp());
	}
}
