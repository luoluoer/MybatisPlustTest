package com.example.demo.Config;

import org.json.JSONArray;
import org.json.JSONObject;

public class returnJson {
	public JSONArray returnbidtitle(JSONObject o) {
		JSONArray j = (JSONArray) o.get("title");
		
		return j;
	} 
}
