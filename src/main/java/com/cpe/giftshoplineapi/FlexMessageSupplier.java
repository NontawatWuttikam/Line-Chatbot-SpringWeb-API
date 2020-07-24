package com.cpe.giftshoplineapi;

import com.google.common.base.Supplier;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexBorderWidthSize;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FlexMessageSupplier {
    public FlexMessage get(String imageUrl, String storeURL, String name, Integer quantity,String description) throws URISyntaxException {
        final Image heroBlock = createHeroBlock(imageUrl);
        final Box bodyBlock = createBodyBlock(name,quantity,description);
        final Box footerBlock = createFooterBlock(storeURL);

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
                .footer(footerBlock)
                .build();
        return new FlexMessage("Restaurant", bubbleContainer);
    }

    private Image createHeroBlock(String url) throws URISyntaxException {
        return Image.builder()
                .url(new URI(url))
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .build();
    }

    private Box createBodyBlock(String name,Integer quantity,String description) {
        final Text title = Text.builder()
                .text(name)
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.LG).wrap(false)
                .flex(10)
                .build();
        final Box info = createInfoBox(quantity,description);

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(title, info))
                .build();
    }

    private Box createInfoBox(Integer quantity,String description) {
        final Box place = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(
                        Text.builder()
                                .text("จำนวนที่มีในคลัง")
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM)
                                .flex(5).wrap(false)
                                .build(),
                        Text.builder()
                                .text(String.valueOf(quantity))
                                .wrap(true)
                                .color("#666666")
                                .flex(5)
                                .build()
                )).contents(Arrays.asList(
                        Text.builder()
                                .text(description)
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM)
                                .flex(5).wrap(false)
                                .build()))
                .build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(place))
                .build();
    }

//    private Box createReviewBox() {
//        final Icon goldStar = Icon.builder()
//                .size(FlexFontSize.SM)
//                .build();
//        final Icon grayStar = Icon.builder()
//                .size(FlexFontSize.SM)
//                .build();
//        final Text point = Text.builder()
//                .text("4.0")
//                .size(FlexFontSize.SM)
//                .color("#999999")
//                .margin(FlexMarginSize.MD)
//                .flex(0)
//                .build();
//
//        return Box.builder()
//                .layout(FlexLayout.BASELINE)
//                .margin(FlexMarginSize.MD)
//                .contents(Arrays.asList(goldStar, goldStar, goldStar, goldStar, grayStar, point))
//                .build();
//    }

    private Box createFooterBlock(String storeUrl) throws URISyntaxException {
        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
        final Button callAction = Button.builder()
                .style(Button.ButtonStyle.LINK)
                .height(Button.ButtonHeight.MEDIUM)
                .action(new URIAction("ไปที่หน้าร้าน", new URI(storeUrl),new URIAction.AltUri(new URI(storeUrl))))
                .build();
        final Separator separator = Separator.builder().build();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(spacer, callAction, separator))
                .build();

    }
}
