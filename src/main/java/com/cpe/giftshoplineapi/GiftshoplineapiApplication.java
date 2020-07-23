package com.cpe.giftshoplineapi;

import com.cpe.giftshoplineapi.controller.TestController;
import com.cpe.giftshoplineapi.handler.MessageHandler;
import com.cpe.giftshoplineapi.service.ProductMessageService;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.ImageMessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.flex.container.FlexContainer;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import com.model.Product;
import com.model.ProductInfo;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@LineMessageHandler
@Configuration
public class GiftshoplineapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftshoplineapiApplication.class, args);
	}


	@Autowired
	ProductMessageService productMessageService;

	LineMessagingClient client;

	@EventMapping
	public List<Message> handleTextMessage(MessageEvent<TextMessageContent> e) throws ExecutionException, InterruptedException {
		System.out.println("event: " + e);
		TextMessageContent message = e.getMessage();
		try {
			Integer queryNumber = Integer.parseInt(message.getText());
			String replyMessage = productMessageService.getSpecificProduct(queryNumber);
			TextMessage textMessage = new TextMessage(replyMessage);
			ProductInfo productInfo = productMessageService.getProduct(queryNumber);
			URI uri = new URIBuilder().setPath(productInfo.getImageURL()).build();
			ImageMessage imageMessage = new ImageMessage(uri,uri);
			PushMessage pushMessage = new PushMessage(e.getSource().getSenderId(),textMessage);
			client.pushMessage(pushMessage);
			return Arrays.asList(textMessage,textMessage);
		}
		catch(NumberFormatException ne) {
			if (message.getText().equals(MessageHandler.RequestHandler.HELP)) {
				return Arrays.asList(new TextMessage(MessageHandler.ReplyHandler.HELP));
			}
			else if (message.getText().equals(MessageHandler.RequestHandler.PRODUCT_LIST)) {
				return Arrays.asList(new TextMessage(productMessageService.getAllProductLineMessage()));
			}
			else if (message.getText().equals(MessageHandler.RequestHandler.PROMOTION)) {
				//return new TextMessage(productMessageService.getAllProductLineMessage());
			}
			else if (message.getText().equals(MessageHandler.RequestHandler.STORE_PAGE))
				return Arrays.asList(new TextMessage(MessageHandler.ReplyHandler.STORE_PAGE));
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (ExecutionException executionException) {
			executionException.printStackTrace();
		} catch (URISyntaxException uriSyntaxException) {
			uriSyntaxException.printStackTrace();
		}
		//ImageMessage img = new ImageMessage();
		//return new TextMessage(message.getText() + "TEST");
		return null;

	}

}
