package com.fmr.pbo.entity;

public class LocateResponse {
	
	private int id;
	private String ticker;
	private String broker;
	private long completed;
	private long pending;
	private long rejected;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public long getCompleted() {
		return completed;
	}
	public void setCompleted(long completed) {
		this.completed = completed;
	}
	public long getPending() {
		return pending;
	}
	public void setPending(long pending) {
		this.pending = pending;
	}
	public long getRejected() {
		return rejected;
	}
	public void setRejected(long rejected) {
		this.rejected = rejected;
	}

}
