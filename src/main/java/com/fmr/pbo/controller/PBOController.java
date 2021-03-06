package com.fmr.pbo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fmr.pbo.entity.LocateRequest;
import com.fmr.pbo.entity.LocateResponse;
import com.fmr.pbo.entity.LongPosition;
import com.fmr.pbo.entity.ShortPosition;
import com.fmr.pbo.entity.TickerDetail;
import com.fmr.pbo.service.PBOService;

@RestController
public class PBOController {

	@Autowired
	@Qualifier("pboServiceImpl")
	private PBOService pboservice;

	@RequestMapping(value = "/longposition", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LongPosition>> getLongPositions(@RequestParam(required = true) final String ticker,
			@RequestParam(required = false) final String broker) {
		List<LongPosition> longPositions = this.pboservice.getLongPosition(ticker, broker);
		return new ResponseEntity<>(longPositions, HttpStatus.OK);
	}

	@RequestMapping(value = "/shortposition", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ShortPosition>> getShortPositions(@RequestParam(required = true) final String ticker,
			@RequestParam(required = false) final String broker) {
		List<ShortPosition> longPositions = this.pboservice.getShortPosition(ticker, broker);
		return new ResponseEntity<>(longPositions, HttpStatus.OK);
	}

	@RequestMapping(value = "/report", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> sendReport(@RequestParam(required = true) final String type) {
		String response = this.pboservice.sendReport(type);
		if ("Success".equalsIgnoreCase(response)) {
			return new ResponseEntity<>("Report has been sent successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Failed to send the report", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@RequestMapping(value = "/tickerdetail", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<TickerDetail> getTickerDetail(@RequestParam(required = true) final String ticker,
			@RequestParam(required = true) final String attribute) {
		TickerDetail locateResponse = this.pboservice.getTickerDetail(ticker, attribute);
		return new ResponseEntity<>(locateResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/locate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> placeLocate(@RequestBody(required = true) final LocateRequest locateRequest) {
		String locateResponse = this.pboservice.placeLocate(locateRequest);
		return new ResponseEntity<>(locateResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/locate", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<LocateResponse>> getLocate() {
		List<LocateResponse> locateResponse = this.pboservice.getLocate();
		return new ResponseEntity<>(locateResponse, HttpStatus.OK);
	}

}
