package com.abn.assignment.model;

import java.util.List;

/** ServiceResponseDataBean - use to send response of REST requests
 * @author MITTAL
 *
 */
public class ServiceResponseDataBean {
	
	

	private String status;
	private String message;
	private List<RecipeDataBean> data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<RecipeDataBean> getData() {
		return data;
	}
	public void setData(List<RecipeDataBean> data) {
		this.data = data;
	}


	
}
