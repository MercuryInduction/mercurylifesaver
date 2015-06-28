package com.mercury.onAccident;

import java.util.ArrayList;
import java.util.TreeMap;

import com.google.gson.JsonObject;
import com.mercury.dataBase.connection.QueryDataBase;
import com.mercury.location.copy.OnLocationRequest;
import com.mercury.sse.KandyDetail;
import com.mercury.sse.ServerEvent;

public class OnAccidentRequest {

	public OnAccidentRequest(){
		System.out.println("In class OnAccidentRequest");
	}
	
	/*****************************************
	 * 
	 * Step1: Search google with coordinate for nearest Hospital (Done)
	 * Step2: Search Database to find Insurance for the UID (Done)
	 * Step3: Search Database and Broadcast SMS to Helpers (Broadcast  pending)
	 * Step4: Initate WebRtc Call Link to both parties (Push notification Pending)
	 * 
	 *******************************************************/
	
	public void accident(String uid,String lat,String lon, String x, String y, String z, String speed){
		 KandyDetail kd=new KandyDetail();
		 kd.setKandyUser(kandyUser);
		 OnLocationRequest lr = new OnLocationRequest();
		 
		 /* It contains the list of nearest hospitals */
		 TreeMap<String, Double> sorted_hospital =lr.searchObjects(lat, lon,"hospital");
		 System.out.println("Nearest Hospitals : "+sorted_hospital);
		 
		 /* It contains the list of nearest medical stores */
		 TreeMap<String, Double> sorted_medicalStore =lr.searchObjects(lat, lon,"pharmacy");
		 System.out.println("Nearest MedicalStore : "+sorted_medicalStore);

		 
		 /*** 
			 * 
			 * make DB query and find details of Helpers corresponding to the uid
			 * 
			 */
	
		 QueryDataBase qdHelpers = new QueryDataBase();
		 ArrayList<JsonObject> helpersList;
		 helpersList=qdHelpers.findHelpersListForVictim(lat,lon);
		if(helpersList!=null)
		 System.out.println("\n\n\n\n\n\n\nHelpers for Victim are :"+helpersList+"\n\n\n\n\n");
		else{
			 System.out.println("\n\n\n\n\n\n\nHelpers for Victim are :"+helpersList+"\n\n\n\n\n");
		}
		 
		 
		/*** 
		 * 
		 * make DB query and find details of Insurance corresponding to the uid
		 * 
		 */
		 QueryDataBase qdInsurance = new QueryDataBase();
		 JsonObject insurance;
		 insurance=qdInsurance.findInsuranceForVictim("uid");
		 
		if(insurance!=null) 
		  System.out.println("Insurance for Victim is :"+insurance.toString());
		else{
			 System.out.println("Insurance for Victim is null ");
		}
		 
    
	try{
		//TODO find insurance number from the reponse 
	    //	String insuranceNumber = insurance.get("Number").getAsString();
		// System.out.println("insuranceNumber"+insurance.get("Number"));

		BroadcaseSMS bs = new BroadcaseSMS();
		bs.sendToHelpers(uid, helpersList, lat, lon);	 
		 
		/*
		 * 
		 * Check who accepts the help invitation -- helperName ????????????????????????????????
		 * destNumber-- ambulance/car/hospital/insurance/medical
		 */
		
		String destNumber="112";//uid.getNumber() //all corresponding numbes
		
		bs.sendToAmbulance(uid, "helperName", lat,lon, destNumber);
		bs.sendToCar(uid, "helperName", lat,lon, destNumber);
		bs.sendToHospital(uid,"helperName", lat,lon, destNumber);
		bs.sendToMedicalStore(uid,"helperName", lat,lon, destNumber);
		
		////////////////OOOOOOOOOOOOOOOOOKKKKKKKKKKKKKKKKKKKKKK///////////////
	//	bs.sendToInsurance(uid, "helperName", lat,lon,insurance.get("number") );
		
}
catch(Exception io){
	System.out.println("Error: "+io.getMessage());
}
		/*
		String victimName, helperName, location, accidentType;
		
		
		for(Object arrayList:helpers){
			//bs.sendToAmbulance(helpers., helperName, location, accidentType, destNumber);
			
		}
		
		InitiateCall ic = new InitiateCall();*/
	}

	public void saveStartInfo(String uid, String mob, String lat, String lon) {
		// TODO Auto-generated method stub
		
	}
}