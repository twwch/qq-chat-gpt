package com.chtw.controller;

import com.chtw.response.CommRespUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/state")
    public CommRespUtils<String> test() {
        return CommRespUtils.success("这个接口用于测试！");
    }

    @GetMapping(value = "/search")
    public CommRespUtils<List<Map<String, String>>> search(String searchKey) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("value", "黄金糕");
        map.put("label", "黄金糕");
        list.add(map);

        map = new HashMap<>();
        map.put("value", "黄金糕");
        map.put("label", "黄金糕");
        list.add(map);

        map = new HashMap<>();
        map.put("value", "双皮奶");
        map.put("label", "双皮奶");
        list.add(map);

        map = new HashMap<>();
        map.put("value", "蚵仔煎");
        map.put("label", "蚵仔煎");
        list.add(map);

        map = new HashMap<>();
        map.put("value", "龙须面");
        map.put("label", "龙须面");
        list.add(map);

        map = new HashMap<>();
        map.put("value", "北京烤鸭");
        map.put("label", "北京烤鸭");
        list.add(map);

        List<Map<String, String>> res = new ArrayList<>();
        for (Map<String, String> stringMap : list) {
            if ((stringMap.get("value").contains(searchKey) && StringUtils.hasText(searchKey)) || !StringUtils.hasText(searchKey)) {
                res.add(stringMap);
            }
        }

        return CommRespUtils.success(res);
    }
}
