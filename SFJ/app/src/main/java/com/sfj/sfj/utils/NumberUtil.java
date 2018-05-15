package com.sfj.sfj.utils;

public class NumberUtil {

	// 钱格式化
	public static String moneyNumberFormat(String number) {
		String tempNum = number;

		// 创建一个变量存储字符串num
		StringBuffer sb = new StringBuffer(tempNum);
		for (int i = sb.length() - 3; i > 0; i = i - 3) {
			// 插入字符串
			sb.insert(i, ",");
		}

		return sb.toString();

	}

	// 手机账号格式化
	public static String phoneNumberFormat(String phone) {
		String reStr = phone.substring(phone.length() - 4, phone.length());
		String preStr = phone.substring(0, phone.length() - 8);
		StringBuilder sb = new StringBuilder();
		sb.append(preStr).append("****").append(reStr);
		return sb.toString();
	}
	
	
	// 身份证号格式化
	public static String IDNumberFormat(String ID) {
				
		StringBuilder sb = new StringBuilder();
		
		sb.append(ID.substring(0,4)).append("**************");
		
		return sb.toString();
	}

}
