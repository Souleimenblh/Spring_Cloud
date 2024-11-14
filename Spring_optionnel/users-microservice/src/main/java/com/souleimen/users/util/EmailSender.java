package com.souleimen.users.util;


public interface EmailSender {
	void sendEmail(String toEmail, String body);
}
