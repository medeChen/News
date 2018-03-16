package com.example.server;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class JsonParser<T> {

	public T parserJsonBean(String jsonString, Class<T> bean) {
		try {
			jsonString = "[" + jsonString + "]";
			//jsonString = jsonString.replaceAll("null", "null");
			return JSONObject.parseArray(jsonString, bean).get(0);
		} catch (Exception e) {
            return null;
		}
	}

	public List<T> parserJsonList(String jsonString, Class<T> bean) {
		try {
			return JSONObject.parseArray(jsonString, bean);
		} catch (Exception e) {
			return null;
		}
	}

}
