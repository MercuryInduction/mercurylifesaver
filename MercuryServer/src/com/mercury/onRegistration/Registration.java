package com.mercury.onRegistration;

import com.clusterpoint.ClusterPointOperation;

/*
 * DEFINITION AND DESCRIPTION -- Raktim
 *  
 * Registration of EndUsers(Helpers)
 * Registration of Hospitals
 * Registration of Insurance
 *  Registration of Medical Hospital
 * 
 * Each Function accepts different parameters
 * return 0---> OK  -1 ---->NOK
 * 
 */



public class Registration {
	
	public static void main(String g[]){
		Registration nr=new Registration();
		int t=nr.registerUser( "234", "234","234","234","234","234","234","234");
	}
	/* Constructor of Registration */
	public Registration(){
		System.out.println("In Registration");
	}
	
	/*Registration for Car Users */
	public int registerUser(String uid,String username,String password,String carNo,String insuranceNo,String insurance){
	
		return 0;
	}
	
	/*Registration for Insurance*/
	public int registerUser(String uid, String username, String password, String compName){
		return 0;
	}
	
	/*Registration for End User */
	public int registerUser(String uid, String username, String password, String mob, String address, String pin, String insuranceNo, String insureCompName){

		String payload="[{\"id\":\"200\"}]";	
		ClusterPointOperation clstrOP=new ClusterPointOperation("AngelUser",payload);	
		clstrOP.create();
			return 0;
	}
	
	/*Registration for Hospital*/
	public int registerUser(String uid,String username,String password, String hospitalName, String hospitalLoc){
		return 0;
	}
	/*Registration for Medical Store*/
	public int registerUser(String uid,String username,String password){
		return 0;
	}
	
	
	/*****
	 * 
	 * Registration and login for HOSPITAL
	 * 
	 * @param uid
	 * @param username
	 * @param password
	 * @param contactNumber
	 * @param hospitalName
	 * @param hospitalLoc
	 * @return O and 1
	 */
	
	public  int registerHospitalUser(String uid, String username,
			String password, String contactNumber, String hospitalName,
			String hospitalLoc) {
		
		
			//String payload="[{\"id\":\""+uid+"\"}]";
			String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s,, \"%s\":%s,, \"%s\":%s,}]", 
					"id", uid, "username", username, "password", password, "contactNumber", contactNumber, "hospitalName", hospitalName, "hospitalLoc", hospitalLoc  );
		    ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryHospital",payload);	
		    int response=clstrOP.create();
		    if(response!=-1){
				response=0;
			}
		    return response;
	}
	
	public int loginHospitalUser(String uid, String username, String password) {
		
		String payload=uid;
	//	String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s}]", "id", uid, "username", username, "password", password);
		System.out.println("In Login"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryHospital",payload);	
		StringBuffer response=clstrOP.search();
		int responseCode=0;
		
		if(response == null){
			responseCode=-1;
		}
		else if(response.toString().contains("\"error\":null")) 
		{
			responseCode=0;
		}
		else{
			responseCode=-1;
		}
		
		System.out.println("Response sent from loginHospital : "+response);
		return responseCode;
	}
	
	/*
	 * 
	 * INSURANCE
	 * 
	 * 
	 */
	
	public int loginInsuranceUser(String uid, String username, String password) {
		String payload=uid;
		System.out.println("In Login"+payload);
	//	String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s}]", "id", uid, "username", username, "password", password);
		System.out.println("In Login"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryInsurance",payload);	
		StringBuffer response=clstrOP.search();
		int responseCode=0;
		
		if(response == null){
			responseCode=-1;
		}
		else if(response.toString().contains("\"error\":null")) 
		{
			responseCode=0;
		}
		else{
			responseCode=-1;
		}
		
		System.out.println("Response sent from loginHospital : "+response);
		return responseCode;
	}
	
	public int registerInsuranceUser(String uid, String username,
			String password, String compName) {
	
		//String payload="[{\"id\":\""+uid+"\"}]";	
		String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s}]", "id", uid, "username", username, "password", password, "compName", compName);
		System.out.println("In registerInsuranceUser"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryInsurance",payload);	
	    int response=clstrOP.create();
	    if(response!=-1){
			response=0;
		}
	    return response;
	}
	public int registerEndUser(String uid, String username, String password,
			String phone, String address, String pin, String insuranceNo,
			String insureCompName) {
		
		 String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s}]", 
				          "id", uid, "username", username, "password", password, "phone", phone, "address", address, "pin", pin, "insuranceNo", insuranceNo, "insureCompName", insureCompName);
		
		//String payload="[{\"id\":\""+uid+"\"}]";
		 System.out.println("In registerEndUser"+payload);
	    ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryHelper",payload);	
	    int response=clstrOP.create();
	    if(response!=-1){
			response=0;
		}
	    return response;
	}
	
	public int loginEndUser(String uid, String username, String password) {
		String payload=uid;
		System.out.println("In Login"+payload);
	//	String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s}]", "id", uid, "username", username, "password", password);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryHelper",payload);	
		StringBuffer response=clstrOP.search();
		int responseCode=0;
		
		if(response == null){
			responseCode=-1;
		}
		else if(response.toString().contains("\"error\":null")) 
		{
			responseCode=0;
		}
		else{
			responseCode=-1;
		}
		
		System.out.println("Response sent from MercuryHelper : "+response);
		return responseCode;
	}
	
	public int registerCarUser(String uid, String username, String password,
			String carNo, String insuranceNo, String insurance) {
	
	
	 String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s}]", 
			          "id", uid, "username", username, "password", password, "carNo", carNo, "insuranceNo", insuranceNo, "insurance", insurance);
	
	
	System.out.println("In registerEndUser"+payload);
    ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryCar",payload);	
    int response=clstrOP.create();
    if(response!=-1){
		response=0;
	}
    return response;
	}
	
	
	public int registerKandy(String id, String username, String password,
			String key, String provider, String uid) {
		
		String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s}]", 
		          "id", id, "username", username, "password", password, "key", key, "provider", provider, "uid", uid);


		System.out.println("In registerKandy"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryKandy",payload);	
		int response=clstrOP.create();
		if(response!=-1){
			response=0;
		}
		return response;
	}
	
	
	public int registerCoupons(String id, String couponcode, String allocated,
			String used) {
		String payload = String.format("[{\"%s\":%s, \"%s\":%s, \"%s\":%s, \"%s\":%s}]", 
		          "id", id, "couponcode", couponcode, "allocated", allocated, "used", used);


		System.out.println("In registerCoupons"+payload);
		ClusterPointOperation clstrOP=new ClusterPointOperation("MercuryCoupons",payload);	
		int response=clstrOP.create();
		if(response!=-1){
			response=0;
		}
		return response;
	}
}
