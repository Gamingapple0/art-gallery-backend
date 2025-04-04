package com.sit331.artgallery.util;

public class verificationUtil {

	public static boolean stringHasValue(String str) {
        return str != null && !str.isBlank();
	}
	
	public static boolean floatHasValue(Float num) {
        return num != null && num > 0;
	}
}
