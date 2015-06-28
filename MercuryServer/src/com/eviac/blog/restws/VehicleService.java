package com.eviac.blog.restws;

import com.mercury.onAccident.OnAccidentRequest;
import com.mercury.onRegistration.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("vehicleservice")
public class VehicleService {

 /******************************************************************
 * 
 * 			ACCIDENT INFORMATION URL FROM CAR MOBILE APP
 * 					CAR MOBILE APP
 * 			THIS URL IS HIT ON START OF CAR
 * 
 ******************************************************************/		
	
	@GET	
	@Path("/start/{uid}&{mob}&{lat}&{lon}/")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject start(@PathParam("uid") String uid,
			@PathParam("mob") String mob,
			@PathParam("lat") String lat,
			@PathParam("lon") String lon){
		 JSONObject res =new JSONObject();
		 System.out.println("Car started for"+uid);
		 OnAccidentRequest onAccidentRequest = new OnAccidentRequest();
		 onAccidentRequest.saveStartInfo(uid,mob,lat,lon);
		 try {
			res.append("result","200");
			res.append("reason","Have a Safe Journey");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return res;
	}
	
	
	/******************************************************************
	 * 
	 * 			ACCIDENT INFORMATION URL FROM CAR MOBILE APP
	 * 					CAR MOBILE APP
	 * 			THIS URL IS HIT ON START OF CAR
	 * 
	 ******************************************************************/		
		
		@GET	
		@Path("/accident/{uid}&{lat}&{lon}&{x}&{y}&{z}&{speed}/")
		@Produces(MediaType.APPLICATION_JSON)
		public JSONObject accident(@PathParam("uid") String uid,
				@PathParam("lat") String lat,
				@PathParam("lon") String lon,
				@PathParam("x") String x,
				@PathParam("y") String y,
				@PathParam("z") String z,
				@PathParam("speed") String speed)
		      {
			 JSONObject res =new JSONObject();
			 System.out.println("Accident Occured for "+uid);
			 OnAccidentRequest onAccidentRequest = new OnAccidentRequest();
			 onAccidentRequest.accident(uid,lat,lon,x,y,z,speed);
			 try {
				res.append("result","200");
				res.append("reason","Dont Panic,We Will Help You");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 return res;
		}
}
