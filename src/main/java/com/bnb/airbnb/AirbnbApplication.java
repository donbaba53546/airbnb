package com.bnb.airbnb;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AirbnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirbnbApplication.class, args);
	}


	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}
	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl(); // optionally set properties here
	}
//	@Value("${twilio.account.sid}")
//	private String accountSid;
//
//	@Value("${twilio.auth.token}")
//	private String authToken;
//	@Bean
//	public void initTwilio() {
//		Twilio.init(accountSid, authToken);
//	}

}

