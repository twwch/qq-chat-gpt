package com.chtw.openai;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OpenAISdk {
    private static String CONTENT_TYPE = "application/json";

    @Value("${open_ai.host}")
    public String HOST;

    @Value("${open_ai.key}")
    public String APIKEY;

    public static boolean isJSON2(String str) {
        boolean result = false;
        try {
            JSONUtil.parseObj(str);
            result = true;
        } catch (Exception ignored) {
        }
        return result;
    }

    public JSONObject doPost(String url, Object data) {
        System.out.printf("HOST: %s%n", HOST);
        System.out.printf("APIKEY: %s%n", APIKEY);
        Map<String, String> headers = new HashMap<>();
        headers.put(Header.CONTENT_TYPE.toString(), CONTENT_TYPE);
        headers.put("Authorization", String.format("Bearer %s", APIKEY));
        JSONObject json = JSONUtil.parseObj(data, false);
        String result = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, CONTENT_TYPE)
                .headerMap(headers, true)
                .body(json.toString())
                .timeout(20000)
                .execute().body();
        if (isJSON2(result)) {
            return JSONUtil.parseObj(result);
        }
        return null;
    }

    public String getOpenAIReply(String content) {
        try {
            String prompt = "你是 ChatGPT, 一个由 OpenAI 训练的大型语言模型, 你旨在回答并解决人们的任何问题，并且可以使用多种语言与人交流。\n请回答我下面的问题\nQ: " + content + "\nA: ";
            Map<String, Object> data = new HashMap<>();
            data.put("model", "gpt-3.5-turbo");
            data.put("prompt", prompt);
            data.put("max_tokens", 2048);
            data.put("temperature", 0.9);
            data.put("frequency_penalty", 0.0);
            data.put("presence_penalty", 0.0);
            data.put("top_p", 1);
            data.put("stop", new String[]{"#"});
            String url = String.format("%s/v1/completions", HOST);
            JSONObject result = this.doPost(url, data);
            System.out.println(result);
            Choices choices = result.getJSONArray("choices").get(0, Choices.class);
            return choices.getText();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public String getOpenAIReplyV2(String content) {
        try {
            String prompt = "你是 ChatGPT, 一个由 OpenAI 训练的大型语言模型, 你旨在回答并解决人们的任何问题，并且可以使用多种语言与人交流。\n请回答我下面的问题\nQ: " + content + "\nA: ";
            Map<String, Object> data = new HashMap<>();
            data.put("model", "gpt-3.5-turbo");
            data.put("messages", new Messages[]{
                    new Messages("user", prompt)
            });
            String url = String.format("%s/v1/completions", HOST);
            JSONObject result = this.doPost(url, data);
            System.out.println(result);
            Choices choices = result.getJSONArray("choices").get(0, Choices.class);
            return choices.getContent();
        } catch (Exception e) {
            return e.getMessage();
        }

    }

}
