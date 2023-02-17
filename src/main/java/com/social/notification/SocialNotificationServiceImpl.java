package com.social.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SocialNotificationServiceImpl implements SocialNotificationService
{

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	String userName;

	@Override
	public boolean sendSimpleMail(SocialNotificationDetails details)
	{
		try
		{
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(userName);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());

			javaMailSender.send(mailMessage);
			return true;
		}

		// Catch block to handle the exceptions
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendMailWithAttachment(SocialNotificationDetails details)
	{
		return false;
	}

}
