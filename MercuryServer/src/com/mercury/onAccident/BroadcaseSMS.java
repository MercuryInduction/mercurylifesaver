package com.mercury.onAccident;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.google.gson.JsonObject;
import com.mercury.support.MessageTemplate;


/***********************************************
 * 
 * 	Broadcasts SMS to all Users
 *  	 Helpers
 *  
 * 
 ***********************************************/
public class BroadcaseSMS {

	private String victimName;
	private String helperName;
	private String location;
	private String accidentType;
	
	public BroadcaseSMS(){
		
	}
    public BroadcaseSMS(String victimName,
    					String helperName,
    					String location,
						String accidentType){
		this.victimName=victimName;
		this.helperName=helperName;
		this.location=location;
		this.accidentType=accidentType;
	
	}
	
	/* Method to send SMS */
	private void send(String destNumber, String message) {
		
		System.out.println("Successfullt send");
	}

	public void sendToHospital(String victimName, String helperName,String latitude,String longitude, String destNumber) {
		MessageTemplate mt = new MessageTemplate();
		String message=mt.createMessage(victimName, helperName, latitude,longitude, "hospital");
		send(destNumber,message);
	}
	
	public void sendToInsurance(String victimName, String helperName,String latitude,String longitude, String destNumber) {
		MessageTemplate mt = new MessageTemplate();
		String message=mt.createMessage(victimName, helperName, latitude,longitude, "Insurance");
		send(destNumber,message);	
	}

	public void sendToHelpers(String victimName,  ArrayList<JsonObject> helpers,String latitude,String longitude) {
		MessageTemplate mt = new MessageTemplate();
		
		for (int i = 0; i < helpers.size(); i++) {
			String message=mt.createMessage(victimName, helpers.get(i).get("phone").toString(), latitude,longitude, "Helpers");
		   
			System.out.println("papa "+helpers.get(i).get("phone"));
			send(helpers.get(i).get("phone").toString(),message);
		}
	}
	
	public void sendToCar(String victimName, String helperName,String latitude,String longitude, String destNumber) {
		MessageTemplate mt = new MessageTemplate();
		String message=mt.createMessage(victimName, helperName, latitude,longitude, "Car");
		send(destNumber,message);		
	}
	public void sendToAmbulance(String victimName, String helperName,String latitude,String longitude, String destNumber) {
		MessageTemplate mt = new MessageTemplate();
		String message=mt.createMessage(victimName, helperName, latitude,longitude, "Ambulance");
		send(destNumber,message);
		
		
	}
	public void sendToMedicalStore(String victimName, String helperName,String location,String accidentType, String destNumber) {
		// TODO Auto-generated method stub
		
	}
}
