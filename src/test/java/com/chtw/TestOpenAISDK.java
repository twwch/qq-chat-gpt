package com.chtw;

import com.chtw.openai.OpenAISdk;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestOpenAISDK {
    OpenAISdk sdk = new OpenAISdk();

    @Test
    public void TestGetOpenAIReplyV2(){

        System.out.println(sdk.getOpenAIReplyV2("你好"));
    }
}
