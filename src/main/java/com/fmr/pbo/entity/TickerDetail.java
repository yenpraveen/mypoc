package com.fmr.pbo.entity;

public class TickerDetail {
	private String ticker;
	private String attribute;
	private String value;
	private String tickerDesc;
	private String attributeDesc;
	

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getTickerDesc() {
		return tickerDesc;
	}

	public void setTickerDesc(String tickerDesc) {
		this.tickerDesc = tickerDesc;
	}

	public String getAttributeDesc() {
		return attributeDesc;
	}

	public void setAttributeDesc(String attributeDesc) {
		this.attributeDesc = attributeDesc;
	}
}
