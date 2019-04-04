package com.novel.Entity;

public class User {

	private int id;
	private String name;
	private String psd;
	private String phone;
	private String like;
	private String history;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	
	public String toString() {
		return "User [name=" + name + ", psd=" + psd + ", phone=" + phone + ", like=" + like + ", history=" + history + "]";
	}
	
	
}
