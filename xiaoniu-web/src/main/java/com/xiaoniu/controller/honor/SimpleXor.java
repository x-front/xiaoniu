package com.xiaoniu.controller.honor;

import com.alibaba.fastjson.JSONObject;

public class SimpleXor {
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		obj.put("Str", "liumao");
		obj.put("number", "123");
		obj.put("symbol", ":#$%^");
		String src = obj.toJSONString();
		System.out.println("src is:"+src);
		String xorStr = xor(src);
		System.out.println("xorStr is:"+xorStr);
		String deXorStr = xor(xorStr);
		System.out.println("deXorStr is:"+deXorStr);
	}
	
	public static String xor(String src){
		if(src == null){
			return null;
		}
		byte[] bytes = src.getBytes();
		for(int i=0; i<bytes.length; i++){
			bytes[i] = (byte) ((~bytes[i] & 0x0F) | (0xF0 & bytes[i] ));
		}
		return new String(bytes);
	}
}
