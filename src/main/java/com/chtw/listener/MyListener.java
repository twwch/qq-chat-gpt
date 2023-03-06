package com.chtw.listener;


import com.chtw.openai.OpenAISdk;
import love.forte.simboot.annotation.ContentTrim;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MyListener {

    @Autowired
    OpenAISdk openAISdk;


    @Listener
    @ContentTrim
    public void FriendListener(FriendMessageEvent event) {
        String reply = openAISdk.getOpenAIReplyV2(event.getMessageContent().getPlainText());
        event.replyBlocking(reply);
    }

    // 只处理@机器人的消息
    @Listener
    @Filter(targets = @Filter.Targets(atBot = true))
    @ContentTrim
    public void GroupListener(GroupMessageEvent event) {
        String reply = openAISdk.getOpenAIReplyV2(event.getMessageContent().getPlainText());
        event.replyBlocking(reply);
    }

}
