package com.example.demo.Common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ComMessage {
	
	public static final Map PLANET_MASTER;
	 
    static {
        HashMap<String, String> msgMap = new HashMap<String, String>();
        msgMap.put("msgNo001", "必須項目が含まれています。");
        msgMap.put("msgNo002", "入力項目が誤っています。");

        PLANET_MASTER = Collections.unmodifiableMap(msgMap);
    }
	
	
	public String getMessage(String msgNo) {

		return (String) PLANET_MASTER.get(msgNo);
	}
}
