package com.social.profile.notification;

public interface ProfileNotificationService
{
	public boolean sendSimpleMail(ProfileNotificationDetails details);

	public boolean sendMailWithAttachment(ProfileNotificationDetails details);
}
