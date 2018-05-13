package com.fmr.pbo.service;

import java.util.List;

import com.fmr.pbo.entity.LocateRequest;
import com.fmr.pbo.entity.LocateResponse;
import com.fmr.pbo.entity.LongPosition;
import com.fmr.pbo.entity.ShortPosition;
import com.fmr.pbo.entity.TickerDetail;


public interface PBOService {
	
	public List<LongPosition> getLongPosition(String ticker,String broker);
	
	public List<ShortPosition> getShortPosition(String ticker,String broker);
	
	public String createLongPositionReport();

	public List<LocateResponse> getLocate();

	public String placeLocate(LocateRequest locateRequest);

	public TickerDetail getTickerDetail(String ticker, String attribute);

	public String createShortPositionReport();
	

 }

