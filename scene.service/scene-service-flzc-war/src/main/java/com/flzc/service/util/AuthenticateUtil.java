package com.flzc.service.util;

import java.util.Random;
import java.util.UUID;

public class AuthenticateUtil {
	
	public static String creatEmailText(){
		return UUID.randomUUID().toString();  
	}
	
	public static String creatMobileText(){
		Random random = new Random(); 
		String sRand=""; 
		for (int i=0;i<6;i++){ 
			String rand=String.valueOf(random.nextInt(10));   
			sRand+=rand; 
		}
		return sRand;
	}
	
	public static void main(String[] args){
		System.out.println(creatMobileText());
	}
}
