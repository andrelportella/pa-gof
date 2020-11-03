package br.com.pa.gof.common.util;

import java.util.ResourceBundle;

public class ResourceUtil {
	private static ResourceUtil resourceUtil = null;
	private ResourceBundle resouce = null;
	
	private ResourceUtil() {
		resouce = ResourceBundle.getBundle("config");
	}
	
	public static ResourceUtil getInstance() {
		if(resourceUtil == null) {
			resourceUtil = new ResourceUtil();
		}
		return resourceUtil;
	}
	
	public String getProperty(String chave) {
		return resouce.getString(chave);
	}
	
}
