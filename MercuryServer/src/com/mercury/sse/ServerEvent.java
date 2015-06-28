package com.mercury.sse;

import java.io.IOException;
import java.io.PrintWriter;
 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
public class ServerEvent extends HttpServlet {
     
 
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
    	KandyDetail kandydetail=new KandyDetail();
    	String kandyUserId=kandydetail.getKandyUser();
    	System.out.println("Kandy id is "+kandyUserId);
    	if(kandyUserId!=null){
    		  //content type must be set to text/event-stream
            response.setContentType("text/event-stream");   
     
            //encoding must be set to UTF-8
            response.setCharacterEncoding("UTF-8");
     
            PrintWriter writer = response.getWriter();
//            writer.write("data: "+ System.currentTimeMillis() +"\n\n");
            writer.write("data: "+ kandyUserId +"\n\n");

//            for(int i=0; i<10; i++) {
//     
//                writer.write("data: "+ System.currentTimeMillis() +"\n\n");
//     
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            writer.close();
    	}else{
    		System.out.println("Error is value No kandy API found");
    	}
      
    }

	private void sendEvent() {
		// TODO Auto-generated method stub
		
	}
    
    
}