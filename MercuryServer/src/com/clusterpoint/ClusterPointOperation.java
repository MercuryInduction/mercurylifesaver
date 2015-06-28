/**
 * 
 */
package com.clusterpoint;

import com.mercury.http.HttpURLConnectionExample;

/**
 * @author Mrityunjay
 *
 */
public class ClusterPointOperation {

	/**
	 * url=https://api-eu.clusterpoint.com/+clusterpointID+this.DBName+"/"+payload;
	 */
	String baseURL="https://api-eu.clusterpoint.com/";
	String clusterpointID="972/";
	String DBName="";
	String url="";
	String payload="";
	String finalPayload="";
	
	HttpURLConnectionExample http = new HttpURLConnectionExample(url);
	public ClusterPointOperation(String DBName,String payload) {
		this.DBName=DBName;
		this.payload=payload;
	}
	
	
	public int create(){
		//https:api-eu.clusterpoint.com/17/Test3/.json[{"id":"47","Category":"Technique"}
		
		url=baseURL+clusterpointID+this.DBName+"/.json";
		int response=10;
		HttpURLConnectionExample http = new HttpURLConnectionExample(url);
		try {
			response=http.sendPost(url,this.payload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public void delete(){
		
	}
	
	public StringBuffer search(){
		url=baseURL+clusterpointID+this.DBName+"/"+payload;
		HttpURLConnectionExample http = new HttpURLConnectionExample(url);
		 StringBuffer response = null ;
		try {
			 response=http.sendGetR(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("In search "+response);
		return response;
	}
	
	public void update(){
		
	}
}
