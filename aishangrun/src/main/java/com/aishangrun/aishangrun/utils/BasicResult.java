package com.aishangrun.aishangrun.utils;

public class BasicResult {
	
	protected int status = 0;
	
	protected String msg = "";
	
	public BasicResult() {
	}
	
	public BasicResult(int status) {
		this.status = status;
	}
	
	public BasicResult(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
