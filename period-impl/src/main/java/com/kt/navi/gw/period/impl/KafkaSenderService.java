package com.kt.navi.gw.period.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class KafkaSenderService {

//    @Autowired
//    private KafkaSender sender;
//
//    @Value("${kafka.topic.unifiedLog}")
//    private String topicUnifiedLog;
//
//    public void unifiedLog(List<Map<String, Object>> content) {
//        Map<String, Object> message = new LinkedHashMap<>();
//        sender.send(topicUnifiedLog, message);
//    }
}
