package com.eviac.blog.restws;

import com.mercury.onRegistration.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("registrationservice")
public class RegistrationService {
	
	
	
 /******************************************************************
 * 
 * 			REGISTRATION URL FOR VARIOUS USERS
 * 			1. END USERS [Helpers]
 * 			2. INSURANCE
 * 			3. HOSTPITALS
 * 			4. CAR MOBILE APP
 * 			5. MEDICAL STORE
 * 
 * *****************************************************************/	
		
	@GET	
	@Path("/registerEndUser/{uid}&{username}&{password}&{phone}&{address}&{pin}&{insuranceNo}&{insureCompName}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerEndUser(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password,
			               @PathParam("phone") String phone,
			               @PathParam("address") String address,
			               @PathParam("pin") String pin,
			               @PathParam("insuranceNo") String insuranceNo,
			               @PathParam("insureCompName") String insureCompName){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Register Helper");
		 
		 Registration registration = new Registration();
		 int response=registration.registerEndUser(uid,username,password,phone,address,pin,insuranceNo,insureCompName);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	@GET	
	@Path("/loginEndUser/{uid}&{username}&{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerEndUser(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password
			              ){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Login Helper");
		 
		 Registration registration = new Registration();
		 int response=registration.loginEndUser(uid,username,password);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	
	@GET	
	@Path("/registerHospital/{uid}&{username}&{password}&{contact}&{hospialName}&{hospitalLoc}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerHospital(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password,
			               @PathParam("contact") String contact,
			               @PathParam("hospialName") String hospitalName,
			               @PathParam("hospitalLoc") String hospitalLoc){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Register Hospital");
		 
		 Registration registration = new Registration();
		 int response=registration.registerHospitalUser(uid,username,password,contact,hospitalName,hospitalLoc);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	@GET	
	@Path("/loginHospital/{uid}&{username}&{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject loginHospital(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password
			              ){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Login Hospital");
		 
		 Registration registration = new Registration();
		 int response=registration.loginHospitalUser(uid,username,password);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	
	@GET	
	@Path("/registerInsurance/{uid}&{username}&{password}&{compName}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerInsurance(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password,
			               @PathParam("compName") String compName){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Register Insurance");
		 
		 Registration registration = new Registration();
		 int response=registration.registerInsuranceUser(uid,username,password,compName);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	@GET	
	@Path("/loginInsurance/{uid}&{username}&{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerInsurance(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password
			             ){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Login Insurance");
		 
		 Registration registration = new Registration();
		 int response=registration.loginInsuranceUser(uid,username,password);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	
	
	
	
	@GET	
	@Path("/registerCar/{uid}&{username}&{password}&{carNo}&{insuranceNo}&{insurance}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerCar(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password,
			               @PathParam("carNo") String carNo,
			               @PathParam("insuranceNo") String insuranceNo,
			               @PathParam("insurance") String insurance){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Register Car");
		 
		 Registration registration = new Registration();
		 int response=registration.registerCarUser(uid,username,password,carNo,insuranceNo,insurance);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	
	
	
	
	@GET	
	@Path("/registerMedicalStrore/{uid}&{username}&{password}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerMedicalStore(@PathParam("uid") String uid,
			               @PathParam("username") String username,
			               @PathParam("password") String password){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside Register Car");
		 
		 Registration registration = new Registration();
		 int response=registration.registerUser(uid,username,password);
		 try {
			if(response==0) {
				res.append("result","200");
				res.append("response","Successfully Registered");
			}
			else{
				res.append("result","400");
				res.append("response","Not Registered");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
}