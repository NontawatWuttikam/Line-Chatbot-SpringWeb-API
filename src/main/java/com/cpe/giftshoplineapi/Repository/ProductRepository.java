package com.cpe.giftshoplineapi.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.model.ProductInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class ProductRepository {

    public static final String COL_NAME="ProductInfo";

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
            String storeURL = (String) queryDocumentSnapshot.getData().get("StoreURL");
            String imageURL = (String) queryDocumentSnapshot.getData().get("ImageURL");
            productInfos.add(new ProductInfo(productName,Quantity,storeURL,imageURL));
        }

        return productInfos;
    }
}
