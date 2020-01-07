package com.lan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/getEs/v1")
    public Map<String, Object> helloWorld(){
        Map<String, Object> res = new HashMap<>();

        JSONArray clips = new JSONArray();
        JSONObject clip = new JSONObject();
        clip.put("groupId", 1);
        clip.put("title", "“回天有我”出实招，民主议事解难题！");
        clip.put("type", new Double(3.0));
        clip.put("sttr","1");
        clip.put("data", Arrays.asList(new HashMap() {{ put("id", "000"); put("address", "000"); }}));
        clips.add(clip);

        JSONObject clip2 = new JSONObject();
        clip2.put("groupId", 2);
        clip2.put("title", "“回天有我”出实招，民主议事解难题！");
        clip2.put("type", new Double(3.0));
        clip2.put("sttr","2");
        clip2.put("data", Arrays.asList(new HashMap() {{ put("id", "000"); put("address", "000"); }}));
        clips.add(clip2);

        JSONObject clip3 = new JSONObject();
        clip3.put("groupId", 3);
        clip3.put("title", "“回天有我”出实招，民主议事解难题！");
        clip3.put("type", new Double(3.0));
        clip3.put("sttr","3");
        clip3.put("data", Arrays.asList(new HashMap() {{ put("id", "000"); put("address", "000"); }}));
        clips.add(clip3);

        res.put("msg", "success");
        res.put("code", "0000");
        res.put("data", clips);
        return res;
    }
}
