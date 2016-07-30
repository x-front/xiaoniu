/**
 * @author zxx
 * @time 2016年7月29日上午9:02:15
 */
package com.zxx.common.enums;

/**
 * @author zxx
 * @time 2016年7月29日
 *
 */
public enum MsgCode {
	TRUE(0,"TRUE"),
	FALSE(-1,"FALSE"),
	OPERATOR_SUCCESS(1000,"操作成功"),
	OPERATOR_FAILED(-1000,"操作失败"),
	ADD_SUCCESS(1001,"添加成功"),
	ADD_FAILED(-1001,"添加失败"),
	UPDATE_SUCCESS(1002,"更新成功"),
	UPDATE_FAILED(-1002,"更新失败"),
	DELETE_SUCCESS(1003,"删除成功"),
	DELETE_FAILED(-1003,"删除失败"),
	SAVE_SUCCESS(1004,"保存成功"),
	SAVE_FAILED(-1004,"保存失败");
	
	MsgCode(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}
	private Integer code;
	private String msg;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
