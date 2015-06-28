package com.mercury.location.copy;

import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.mercury.http.HttpURLConnectionExample;

public class Location {
	
	static String baseUrl="https://maps.googleapis.com/maps/api/";
	static String key="AIzaSyCgZzbiNQrB4FCb2l1fJc6HNGdZlcRtEVI";
	
	
	public ArrayList findObjects(String lat,String lon,String radius,String type){
		
		ArrayList<JsonObject> list = new ArrayList<JsonObject>();
		String parameter= "location="+lat+","+lon+"&radius="+radius+"&types="+type;
		list=searchNear(parameter);	
		return list;
		
	}
	
	public Location() {
		
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<JsonObject> searchNear(String payload){

		String apiName="place/nearbysearch";
		String key="AIzaSyCgZzbiNQrB4FCb2l1fJc6HNGdZlcRtEVI";
		
	    String url=baseUrl+apiName+"/json?"+payload+"&key="+key;
	    ArrayList<JsonObject> response = new ArrayList<JsonObject>();
	    
		HttpURLConnectionExample http = new HttpURLConnectionExample(url);
		try {	
			response=http.sendGet(url); //Hospital List
			//http.sendPost(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return response;
	}
	
	}

