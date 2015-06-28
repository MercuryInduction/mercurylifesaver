package com.mercury.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 








import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import com.fasterxml.jackson.core.JsonParser;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
 
public class HttpURLConnectionExample {
   private String url="";
   
	public HttpURLConnectionExample(String url){
		this.url=url;
		
	}
 
	private final String USER_AGENT = "Mozilla/5.0";
 
//	public static void main(String[] args) throws Exception {
// 
//		
//		String key="AIzaSyCgZzbiNQrB4FCb2l1fJc6HNGdZlcRtEVI";
//	    String origin="delhi";
//	    String destination="noida";
//	    String mode="car";
//	    String language="en";
//		String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"+&destinations="+destination+"&mode="+mode+"&language="+language+"&key="+key;
//		HttpURLConnectionExample http = new HttpURLConnectionExample();
//		System.out.println("Testing 1 - Send Http GET request");
//		
//		http.sendGet(url);
// 
//		System.out.println("\nTesting 2 - Send Http POST request");
//		http.sendPost(url);
// 
//	}
 
	// HTTP GET request
	public ArrayList<JsonObject> sendGet(String url) throws Exception {
 
		//String url = "http://www.google.com/search?q=mkyong";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		ArrayList<JsonObject> jo = new ArrayList<JsonObject>();
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		/*parsing starts */
		com.google.gson.JsonParser jp = new com.google.gson.JsonParser();
		JsonElement jE=jp.parse(response.toString());
	    JsonObject Jo=jE.getAsJsonObject();	    
	    JsonArray JA = Jo.getAsJsonArray("results");
 
	    for (int i = 0; i<JA.size(); i++)
	    {
	    	JsonObject pop = new JsonObject();
	    	
	    	JsonElement p=JA.get(i);
	 	    JsonObject jo2 = p.getAsJsonObject();
	 	    pop.add("HospitalName", jo2.get("name"));     
	 	    
	 	    JsonElement loc = jo2.get("geometry");
	 	    JsonObject pp = loc.getAsJsonObject();
	 	   
	 	    pop.add("location", pp.get("location"));	 	  
	 	    jo.add(i, pop);
	 	
	    }
		
		in.close();
 		return jo;
	}
 
	public StringBuffer sendGetR(String url) throws Exception {
		 
		//String url = "http://www.google.com/search?q=mkyong";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		
		String authString = "mercuryinduction@gmail.com" + ":" + "mercuryinduction1";
		System.out.println("auth string: " + authString);
		byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
		String authStringEnc = new String(authEncBytes);
		System.out.println("Base64 encoded auth string: " + authStringEnc);
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		 con.setRequestProperty("Accept", "application/json");
	        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        con.setRequestProperty("Authorization", "Basic " + authStringEnc);
	        //con.setRequestProperty("Authorization", "basic"+"mercuryinduction@gmail.com:mercuryinduction1");
	     
	       
		int responseCode = con.getResponseCode();
	if(responseCode==200){
		
		String responsemessage =con.getResponseMessage();
		
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		System.out.println("Response Message : " + responsemessage);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		   System.out.println(response.toString());
		   System.out.println("Raktim sendGetR:"+responseCode);
	       return response;
	}
	
	else 
		return null;
	
	
	//	{"documents":[{"id":"Raktimsd"}],"found":"1","seconds":0.001223,"error":null}
/*	
		if(!response.toString().contains("\"error\":null")){
		
			responseCode=-1;
		}
		*/
		//print result
	   
	}
 
	
	
	
 
	// HTTP POST request
	public void sendPost(String url) throws Exception {
 
		//String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
		
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
     
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		
		//print result
		System.out.println(response.toString());
		
 
	}
 
	
	

	public int sendPost(String url,String payload) throws Exception {
		//String url = "https://selfsolve.apple.com/wcResults.do";
				URL obj = new URL(url);
				HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
				

				String authString = "mercuryinduction@gmail.com" + ":" + "mercuryinduction1";
				System.out.println("auth string: " + authString);
				byte[] authEncBytes = Base64.encodeBase64(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				System.out.println("Base64 encoded auth string: " + authStringEnc);
		 
				//add reuqest header
				con.setRequestMethod("POST");
				con.setRequestProperty("User-Agent", USER_AGENT);
				 con.setRequestProperty("Accept", "application/json");
			        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			        con.setRequestProperty("Authorization", "Basic " + authStringEnc);
		     
				String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		 
				// Send post request
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(payload);
				wr.flush();
				wr.close();
		 
				int responseCode = con.getResponseCode();
				System.out.println("\nSending 'POST' request to URL : " + url);
				System.out.println("Post parameters : " + payload);
				System.out.println("Response Code : " + responseCode);
				System.out.println("Response message : " + con.getResponseMessage());
				
		 
				BufferedReader in = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
		 
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				if(!response.toString().contains("\"error\":null")){
					
					responseCode=-1;
				}
				//print result
				System.out.println(response.toString());
				return responseCode;
	}
}

