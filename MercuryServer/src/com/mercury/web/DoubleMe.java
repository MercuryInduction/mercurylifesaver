package com.mercury.web;

import java.io.IOException;
import java.io.OutputStreamWriter;
 

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import javax.servlet.annotation.WebServlet;
@WebServlet("/DoubleMeServlet")
public class DoubleMe extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoubleMe() {
        super();
 
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.setContentType("text/xml");
        response.getOutputStream().println("<root><data>call</data></root>");
 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        try {
            int length = request.getContentLength();
            byte[] input = new byte[length];
            ServletInputStream sin = request.getInputStream();
            int c, count = 0 ;
            while ((c = sin.read(input, count, input.length-count)) != -1) {
                count +=c;
            }
            sin.close();
 
            String recievedString = new String(input);
            response.setStatus(HttpServletResponse.SC_OK);
            OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream());
 
            Integer doubledValue = Integer.parseInt(recievedString) * 2;
 
            writer.write(doubledValue.toString());
            writer.flush();
            writer.close();
 
 
 
        } catch (IOException e) {
 
 
            try{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().print(e.getMessage());
                response.getWriter().close();
            } catch (IOException ioe) {
            }
        }  
        }
 
}