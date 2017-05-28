package com.sudha.domains;

import java.util.Base64;
import java.util.Base64.Encoder;

public class Base64Test {
	
	public static void main(String args[]){
		byte[] encoder = Base64.getEncoder().encode("sudha:nalamothu".getBytes());
		System.out.println(new String(encoder));
	}

}
