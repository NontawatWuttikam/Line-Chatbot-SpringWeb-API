package com.cpe.giftshoplineapi.controller;

import com.cpe.giftshoplineapi.FlexMessageSupplier;
import com.cpe.giftshoplineapi.handler.MessageHandler;
import com.cpe.giftshoplineapi.service.ProductMessageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TextMessage;
import com.model.ProductInfo;
import com.cpe.giftshoplineapi.service.FBService;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
@RestController
public class TestController {

    @Autowired
    ProductMessageService productMessageService;

    FlexMessageSupplier flexMessageSupplier = new FlexMessageSupplier();

    public static final String COL_NAME="ProductInfo";

    @GetMapping("/test")
    public List<ProductInfo> getProductInfo() throws InterruptedException, ExecutionException {
//            FirebaseApp firebaseApp = FirebaseApp.getInstance();
//            Firestore dbFirestore = FirestoreClient.getFirestore();
//            CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
//            ApiFuture<QuerySnapshot> future = collectionReference.get();
//
//            QuerySnapshot document = future.get();
//            List<ProductInfo> productInfos = new ArrayList<>();
//            for (QueryDocumentSnapshot queryDocumentSnapshot : document.getDocuments()) {
//                String productName = (String) queryDocumentSnapshot.getData().get("Name");
//                Long Quantity = (Long) queryDocumentSnapshot.getData().get("Quantity");
//                productInfos.add(new ProductInfo(productName,Quantity,));
//            }
//
//            return productInfos;
        return null;
    }

    @GetMapping("/linetest")
    public String testLingMessage() throws ExecutionException, InterruptedException {
        return productMessageService.getAllProductLineMessage();
    }

    @GetMapping("/test2")
    public List<Message> handleTextMessage() throws ExecutionException, InterruptedException {
//        TextMessageContent message = new TextMessageContent("1","1");
//        try {
//            Integer queryNumber = Integer.parseInt(message.getText());
//            String replyMessage = productMessageService.getSpecificProduct(queryNumber);
//            TextMessage textMessage = new TextMessage(replyMessage);
//            ProductInfo productInfo = productMessageService.getProduct(queryNumber);
//            URI uri = new URIBuilder().setPath(productInfo.getImageURL()).build();
////			ImageMessage imageMessage = new ImageMessage(uri,uri);
////			PushMessage pushMessage = new PushMessage(e.getSource().getSenderId(),textMessage);
//            Message flexMessage = flexMessageSupplier.get(productInfo.getImageURL());
//            return Arrays.asList(flexMessage);
//        }
//        catch(NumberFormatException ne) {
//            if (message.getText().equals(MessageHandler.RequestHandler.HELP)) {
//                return Arrays.asList(new TextMessage(MessageHandler.ReplyHandler.HELP));
//            }
//            else if (message.getText().equals(MessageHandler.RequestHandler.PRODUCT_LIST)) {
//                return Arrays.asList(new TextMessage(productMessageService.getAllProductLineMessage()));
//            }
//            else if (message.getText().equals(MessageHandler.RequestHandler.PROMOTION)) {
//                //return new TextMessage(productMessageService.getAllProductLineMessage());
//            }
//            else if (message.getText().equals(MessageHandler.RequestHandler.STORE_PAGE))
//                return Arrays.asList(new TextMessage(MessageHandler.ReplyHandler.STORE_PAGE));
//        } catch (InterruptedException interruptedException) {
//            interruptedException.printStackTrace();
//        } catch (ExecutionException executionException) {
//            executionException.printStackTrace();
//        } catch (URISyntaxException uriSyntaxException) {
//            uriSyntaxException.printStackTrace();
//        }
//        //ImageMessage img = new ImageMessage();
//        //return new TextMessage(message.getText() + "TEST");
//        return null;
        return null;
    }


    @PostConstruct
    public void initialize() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/java/com/cpe/giftshoplineapi/controller/trishudagiftshop-firebase-adminsdk-fz4a5-8c5abbfb1d.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://trishudagiftshop.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
