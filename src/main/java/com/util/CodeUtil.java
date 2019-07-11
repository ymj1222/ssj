package com.util;

public class CodeUtil {
public  synchronized String getCode() {
	long bs = System.currentTimeMillis();
	String code = Long.toString(bs);
	return code;
}
}
