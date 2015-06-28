package com.mercury.location.copy;


import java.util.*;
import java.lang.*;
import java.io.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


 public class OnLocationRequest {

    public  TreeMap<String, Double> searchObjects(String lats,String lons,String object) {

        HashMap<String,Double> map = new HashMap<String,Double>();
    
        ValueComparator bvc =  new ValueComparator(map);
        TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);
	
		Location loc = new Location();
	 
	double lo=Double.parseDouble(lats);
	double ln=Double.parseDouble(lons);
		 
		ArrayList<JsonObject> list = new ArrayList<JsonObject>();
		list= loc.findObjects(lats, lons, "12500", object);
		 for (int i = 0; i < list.size(); i++) {
	  
			    JsonObject obj = list.get(i);
	            JsonElement coordinate=obj.get("location");
	            JsonObject joCo=coordinate.getAsJsonObject();
	       
	            double latitude = Double.parseDouble(joCo.get("lat").toString());            
	            double longitude = Double.parseDouble(joCo.get("lng").toString());
	            
	            Distance d = new Distance();	
	            
                map.put(obj.get("HospitalName").toString(),d.distance(lo, ln, latitude, longitude, 'K'));	            

            //    map.put(obj.get("HospitalName").toString(),d.distance(-33.8670522, 151.1957362, latitude, longitude, 'K'));	            
	        //    System.out.println(" Hospital "+obj.get("HospitalName")+"is at "+d.distance(-33.8670522, 151.1957362, latitude, longitude, 'K')+"Km from the accident spot");
	            
	        }
		    sorted_map.putAll(map);
		    return sorted_map;
	       
	}
}

 
 class ValueComparator implements Comparator<String> {

	    Map<String, Double> base;
	    
	    
	    
	    public ValueComparator(Map<String, Double> base) {
	        this.base = base;
	    }

	    // Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {
	        if (base.get(a) <= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        } // returning 0 would merge keys
	    }
	
}