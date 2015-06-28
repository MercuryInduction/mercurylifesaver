package com.mercury.support;

public class MessageTemplate {

	public String createMessage(String victimName,String helperName,String latitude,String longitude,String destination){
		
		String message=null;
		if(destination.equalsIgnoreCase("Hospital")){
			message=victimName+" had an accident in "+latitude+" "+longitude+"."+helperName+"is ready to help.Please assist asap";
		}
		else if(destination.equalsIgnoreCase("Insurance")){
			message="Your insurance person "+victimName+" had an accident in "+latitude+" "+longitude+"."+helperName+"is ready to help.Please assist asap";
		}
		else if(destination.equalsIgnoreCase("Ambulance")){
			message=victimName+" had an accident in "+latitude+" "+longitude+"Please assist asap";
		}
		else if(destination.equalsIgnoreCase("Helper")){
			message=victimName+" had an accident in your location, "+latitude+" "+longitude+"Please assist asap. Insurance and hospital members will contact you.";
		}
		else{
			message="Please dont panic.We are at your service";
		}
		return message;
	}
}
 