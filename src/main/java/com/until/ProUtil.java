package com.until;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ProUtil {
	private static String rootPath = "";
	private static Properties prop = new Properties();

	private ProUtil() {
		rootPath = this.getClass().getClassLoader().getResource("").getPath();
	}

	public static void main(String[] args) {
		System.out.println(ProUtil.getVideokey());
		System.out.println(ProUtil.getPhotokey());
	}

	private static Properties getv() {
		if ("".equals(rootPath)) {
			new ProUtil();
		}
		if (prop.isEmpty()) {
			try {
				FileInputStream fis = new FileInputStream(rootPath + "xx.properties");
				InputStreamReader isr = new InputStreamReader(fis, "utf-8");
				BufferedReader buffs = new BufferedReader(isr);
				prop.load(buffs);
			} catch (Exception e) {

			}
		}
		return prop;
	}
	public static String getValue(String key) {
		return getv().getProperty(key);
	}
	public static String getPhotokey() {
		return getValue("photopath");
	}
	public static String getVideokey() {
		return getValue("videopath");
	}
}
