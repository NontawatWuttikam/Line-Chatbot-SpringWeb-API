package com.cpe.giftshoplineapi;

import com.google.common.base.Supplier;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.FlexMessage;
import com.linecorp.bot.model.message.flex.component.*;
import com.linecorp.bot.model.message.flex.container.Bubble;
import com.linecorp.bot.model.message.flex.unit.FlexFontSize;
import com.linecorp.bot.model.message.flex.unit.FlexLayout;
import com.linecorp.bot.model.message.flex.unit.FlexMarginSize;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class FlexMessageSupplier {
    public FlexMessage get(String url) throws URISyntaxException {
        final Image heroBlock = createHeroBlock(url);
        final Box bodyBlock = createBodyBlock();
//        final Box footerBlock = createFooterBlock();

        final Bubble bubbleContainer = Bubble.builder()
                .hero(heroBlock)
                .body(bodyBlock)
//                .footer(footerBlock)
                .build();
        return new FlexMessage("Restaurant", bubbleContainer);
    }

    private Image createHeroBlock(String url) throws URISyntaxException {
        return Image.builder()
                .url(new URIBuilder().setPath(url).build())
                .size(Image.ImageSize.FULL_WIDTH)
                .aspectRatio(Image.ImageAspectRatio.R20TO13)
                .aspectMode(Image.ImageAspectMode.Cover)
                .build();
    }

    private Box createBodyBlock() {
        final Text title = Text.builder()
                .text("Brown Cafe")
                .weight(Text.TextWeight.BOLD)
                .size(FlexFontSize.XL)
                .build();
        final Box info = createInfoBox();

        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .contents(Arrays.asList(title, info))
                .build();
    }

    private Box createInfoBox() {
        final Box place = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(
                        Text.builder()
                                .text("Place")
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM)
                                .flex(1)
                                .build(),
                        Text.builder()
                                .text("Silom, Bangkok")
                                .wrap(true)
                                .color("#666666")
                                .flex(5)
                                .build()
                )).build();
        final Box time = Box.builder()
                .layout(FlexLayout.BASELINE)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(
                        Text.builder().text("Time")
                                .color("#aaaaaa")
                                .size(FlexFontSize.SM)
                                .flex(1)
                                .build(),
                        Text.builder()
                                .text("10:00 - 23:00")
                                .wrap(true)
                                .color("#666666")
                                .size(FlexFontSize.SM)
                                .flex(5)
                                .build()
                )).build();
        return Box.builder()
                .layout(FlexLayout.VERTICAL)
                .margin(FlexMarginSize.LG)
                .spacing(FlexMarginSize.SM)
                .contents(Arrays.asList(place, time))
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

//    private Box createFooterBlock() {
//        final Spacer spacer = Spacer.builder().size(FlexMarginSize.SM).build();
//        final Button callAction = Button.builder()
//                .style(Button.ButtonStyle.LINK)
//                .height(Button.ButtonHeight.MEDIUM)
//                .action(new URIAction("CALL", "tel:00000"))
//                .build();
//        final Separator separator = Separator.builder().build();
//        final Button websiteAction = Button.builder()
//                .style(Button.ButtonStyle.LINK)
//                .height(Button.ButtonHeight.SMALL)
//                .action(new URIAction("WEBSITE", "https://example.com"))
//                .build();
//
//        return Box.builder()
//                .layout(FlexLayout.VERTICAL)
//                .spacing(FlexMarginSize.SM)
//                .contents(Arrays.asList(spacer, callAction, separator, websiteAction))
//                .build();
//
//    }
}