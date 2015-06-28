package com.eviac.blog.restws;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.mercury.google.UploadData;


public class TimeTask extends TimerTask{

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hello");
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/* call at 11:59pm */
	//	InsertData id = new InsertData();
	//	id.setGlobalData();
		
		
		
		
		
		UploadData ud = new UploadData();
		
			try {
				ud.downloadFile();
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
 
}
