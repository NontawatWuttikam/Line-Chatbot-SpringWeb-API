package com.cpe.giftshoplineapi.handler;

import org.springframework.stereotype.Component;

public class MessageHandler {

    public class ReplyHandler {
        public static final String STORE_PAGE = "ลูกค้าสามารถเลือกซื้อสินค้าและติดตามโปรโมชั่นดีๆจากทางร้านค้าได้ที่ Facebook Page \n\n"+
                "Facebook Store : shorturl.at/lnqHZ";
        public static final String HELP = "ขั้นตอนการเลือกและซื้อสินค้าผ่านช่องทาง Line \n\n" +
                                    "1. ลูกค้าสามารถเลือกดูสินค้าโดยการกดเมนู \"รายการสินค้า\" \n\n" +
                                    "2. ลูกค้าสามารถดูรายละเอียดสินค้าได้โดยการเลือกหมายเลขหน้าชื่อสินค้า \n\n" +
                                    "3. ลูกค้าสามารถสั่งซื้อได้ผ่านลิงก์ Facebook ที่อยู๋ภายในข้อมูลรายละเอียดสินค้า ";
    }

    public class RequestHandler {
        public static final String PRODUCT_LIST = "รายการสินค้า";
        public static final String PROMOTION = "โปรโมชัน";
        public static final String STORE_PAGE = "เพจร้านค้า";
        public static final String HELP = "ขั้นตอนการซื้อสินค้า";
    }

}
