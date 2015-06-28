package com.eviac.blog.restws;

import com.mercury.onRegistration.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("adminservice")
public class AdminService {
	
	
	
 /******************************************************************
 * 
 * 			REGISTRATION URL FOR VARIOUS USERS
 * 			1. Kandy Details
 * 			2. Rewards
 * 			
 * 
 * *****************************************************************/	
		
	@GET	
	@Path("/registerKandy/{id}&{username}&{password}&{key}&{provider}&{uid}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerEndUser(@PathParam("id") String id,
			               @PathParam("username") String username,
			               @PathParam("password") String password,
			               @PathParam("key") String key,
			               @PathParam("provider") String provider,
			               @PathParam("uid") String uid){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside registerKandy Helper");
		 
		 Registration registration = new Registration();
		 int response=registration.registerKandy(id,username,password,key,provider,uid);
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
	@Path("/registerCoupons/{id}&{couponcode}&{allocated}&{used}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject registerCoupons(@PathParam("id") String id,
			               @PathParam("couponcode") String couponcode,
			               @PathParam("allocated") String allocated,
			               @PathParam("used") String used){
		 JSONObject res =new JSONObject();
		 System.out.println("Inside registerCoupons ");
		 
		 Registration registration = new Registration();
		 int response=registration.registerCoupons(id,couponcode,allocated,used);
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