package com.reinaldo.resources.exceptions;

public class StandardError {

	private Long timestamp;
	private Integer status;
	private String msg;

	public StandardError() {
		super();
	}

	public StandardError(Long timestamp, Integer status, String msg) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.msg = msg;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
