package com.mercury.dataBase.connection;


import java.util.ArrayList;

import org.json.simple.parser.JSONParser;

import com.clusterpoint.ClusterPointOperation;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class QueryDataBase {

	public static void main(String[] args) {
		
		/*QueryDataBase p= new QueryDataBase();
		p.findHelpersListForVictim("-33.8670522","151.1957362");*/
		// TODO Auto-generated method stub

	}

	public JsonObject findInsuranceForVictim(String uid) {
		/*
		 * 
		 * Make Db Queery and find he insurance company details for the uid
		 * Return Name and PhoneNumber
		 * 
		 */
		
		String payload=uid;
		System.out.println("In findInsuranceForVictim"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryInsurance",payload);	
		StringBuffer response=clstrOP.search();
	
		if(response!=null){
		
		com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
		JsonElement jE=jp.parse(response.toString());
	    JsonObject Jo=jE.getAsJsonObject();	    
	    JsonArray JA = Jo.getAsJsonArray("documents");
 

	    JsonObject pop = new JsonObject();
	    JsonElement p=JA.get(0);
 	    JsonObject jo2 = p.getAsJsonObject();
 	    System.out.println("Name "+jo2.get("name")); 
 	    pop.add("name", jo2.get("name"));
 	   
 	     System.out.println("number "+jo2.get("number")); 
 	     pop.add("number", jo2.get("number"));
	    
		return pop;
		}
		return null;
	}

	public ArrayList<JsonObject> findHelpersListForVictim(String lat, String lon) {
		String payload="_search/*";
		System.out.println("In findHelpersListForVictim payload is all "+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryHelper",payload);
		StringBuffer response=clstrOP.search();
	
	if(response!=null ){	
		com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
		JsonElement jE=jp.parse(response.toString());
	    JsonObject Jo=jE.getAsJsonObject();	    
	    JsonArray JA = Jo.getAsJsonArray("documents");
 
	    ArrayList<JsonObject> jo = new ArrayList<JsonObject>();
	    
	    for (int i = 0; i<JA.size(); i++)
	    {
	    JsonObject pop = new JsonObject();
	    JsonElement p=JA.get(i);
 	    JsonObject jo2 = p.getAsJsonObject();
 	    System.out.println("Name "+jo2.get("name")); 
 	    pop.add("name", jo2.get("name"));
 	    System.out.println("Latitude "+jo2.get("lat")); 
 	    pop.add("latitude", jo2.get("lat"));
 	    System.out.println("Longitude "+jo2.get("lon")); 
 	    pop.add("longitude", jo2.get("lon"));
 	    System.out.println("Phone "+jo2.get("phone")); 
 	     pop.add("phone", jo2.get("phone"));
 	    jo.add(i, pop);
	    }

		
		System.out.println("RPG :"+JA.toString());
		return jo;
	}
	return null;
	}

}
