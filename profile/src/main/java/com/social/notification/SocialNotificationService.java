package com.social.notification;

public interface SocialNotificationService 
{
	public boolean sendSimpleMail(SocialNotificationDetails details);

	public boolean sendMailWithAttachment(SocialNotificationDetails details);
}
