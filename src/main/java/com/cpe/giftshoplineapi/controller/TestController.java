package com.cpe.giftshoplineapi.controller;

import com.cpe.giftshoplineapi.service.ProductMessageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.model.ProductInfo;
import com.cpe.giftshoplineapi.service.FBService;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.api.core.ApiFuture;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
@RestController
public class TestController {

    @Autowired
    ProductMessageService productMessageService;

    public static final String COL_NAME="ProductInfo";

    @GetMapping("/test")
    public List<ProductInfo> getProductInfo() throws InterruptedException, ExecutionException {
            FirebaseApp firebaseApp = FirebaseApp.getInstance();
            Firestore dbFirestore = FirestoreClient.getFirestore();
            CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
            ApiFuture<QuerySnapshot> future = collectionReference.get();

            QuerySnapshot document = future.get();
            List<ProductInfo> productInfos = new ArrayList<>();
            for (QueryDocumentSnapshot queryDocumentSnapshot : document.getDocuments()) {
                String productName = (String) queryDocumentSnapshot.getData().get("Name");
                Long Quantity = (Long) queryDocumentSnapshot.getData().get("Quantity");
                productInfos.add(new ProductInfo(productName,Quantity));
            }

            return productInfos;
    }

    @GetMapping("/linetest")
    public String testLingMessage() throws ExecutionException, InterruptedException {
        return productMessageService.getAllProductLineMessage();
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
