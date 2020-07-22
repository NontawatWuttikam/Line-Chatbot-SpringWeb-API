package com.cpe.giftshoplineapi;

import com.cpe.giftshoplineapi.controller.TestController;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@LineMessageHandler
@Configuration
@ComponentScan(basePackages = {"com.cpe.giftshoplineapi.controller"})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class GiftshoplineapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftshoplineapiApplication.class, args);
	}

	@EventMapping
	public Message handleTextMessage(MessageEvent<TextMessageContent> e) {
		System.out.println("event: " + e);
		TextMessageContent message = e.getMessage();
		//ImageMessage img = new ImageMessage();
		return new TextMessage(message.getText());
	}

}
