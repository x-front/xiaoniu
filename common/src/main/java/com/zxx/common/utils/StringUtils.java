/**
 * @author zxx
 * @time 2016年7月29日上午9:30:47
 */
package com.zxx.common.utils;

/**
 * @author zxx
 * @time 2016年7月29日
 *
 */
public class StringUtils {
	/**
	 * 将字符串转换为整数数组
	 * 
	 * @param idsStr
	 * @return
	 */
	public static Integer[] convertStringToIds(String strIds) {
		String[] strs = strIds.split(",");
		Integer[] ids = new Integer[strs.length];
		for (int i = 0; i < strs.length; i++) {
			if (!"".equals(strs[i].trim())) {
				ids[i] = Integer.parseInt(strs[i].trim());
			}
		}
		return ids;
	}
}
