package com.fmr.pbo.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fmr.pbo.entity.LocateRequest;
import com.fmr.pbo.entity.LocateResponse;
import com.fmr.pbo.entity.LongPosition;
import com.fmr.pbo.entity.ShortPosition;
import com.fmr.pbo.entity.TickerDetail;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service("pboServiceImpl")
public class PBOServiceImpl implements PBOService {

	@Override
	public List<LongPosition> getLongPosition(String ticker, String broker) {
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		List<LongPosition> longPositions = new ArrayList<>();

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "")) {
		
			if (StringUtils.isNotBlank(broker)) {
				String sql = "SELECT * FROM TB_ALEXA_PBO_LONG_POS WHERE TICKER = ? and BROKER = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, ticker);
				pstmt.setString(2, broker);

			} else {
				String sql = "SELECT * FROM TB_ALEXA_PBO_LONG_POS WHERE TICKER = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, ticker);
			}
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				LongPosition longPosition = new LongPosition();
				longPosition.setId(resultSet.getInt("ID"));
				longPosition.setTicker(resultSet.getString("TICKER"));
				longPosition.setBroker(resultSet.getString("BROKER"));
				longPosition.setQuantity(resultSet.getLong("QUANTITY"));
				longPositions.add(longPosition);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return longPositions;
	}

	@Override
	public List<ShortPosition> getShortPosition(String ticker, String broker) {
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		List<ShortPosition> shortPositions = new ArrayList<>();
		
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "")) {

			if (StringUtils.isNotBlank(broker)) {
				String sql = "SELECT * FROM TB_ALEXA_PBO_SHORT_POS WHERE TICKER = ? and BROKER = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, ticker);
				pstmt.setString(2, broker);

			} else {
				String sql = "SELECT * FROM TB_ALEXA_PBO_SHORT_POS WHERE TICKER = ?";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, ticker);
			}
			resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				ShortPosition shortPosition = new ShortPosition();
				shortPosition.setId(resultSet.getInt("ID"));
				shortPosition.setTicker(resultSet.getString("TICKER"));
				shortPosition.setBroker(resultSet.getString("BROKER"));
				shortPosition.setQuantity(resultSet.getLong("QUANTITY"));
				shortPositions.add(shortPosition);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return shortPositions;
	}

	@Override
	public String createLongPositionReport() {
		ResultSet resultSet = null;
		Statement statement = null;

		String result = "Success";

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"LongPosition.pdf"));
		} catch (FileNotFoundException | DocumentException e2) {
			e2.printStackTrace();
		}
		document.open();
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Phrase("TICKER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("BROKER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("QUANTITY"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "")) {

			String sql = "SELECT * FROM TB_ALEXA_PBO_LONG_POS";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				table.addCell(resultSet.getString("TICKER"));
				table.addCell(resultSet.getString("BROKER"));
				table.addCell(resultSet.getString("QUANTITY"));
			}
			document.add(table);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		} 
		return result;
	}

	@Override
	public String createShortPositionReport() {
		ResultSet resultSet = null;
		Statement statement = null;

		String result = "Success";

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"ShortPosition.pdf"));
		} catch (FileNotFoundException | DocumentException e2) {
			e2.printStackTrace();
		}
		document.open();
		PdfPTable table = new PdfPTable(3);

		PdfPCell c1 = new PdfPCell(new Phrase("TICKER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("BROKER"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("QUANTITY"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		table.setHeaderRows(1);

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "")) {


			String sql = "SELECT * FROM TB_ALEXA_PBO_SHORT_POS";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				table.addCell(resultSet.getString("TICKER"));
				table.addCell(resultSet.getString("BROKER"));
				table.addCell(resultSet.getString("QUANTITY"));
			}
			document.add(table);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
			result = "Failure";
		} 
		return result;
	}


	
	@Override
	public List<LocateResponse> getLocate() {
		Connection connection = null;
		ResultSet resultSet = null;
		Statement statement = null;

		List<LocateResponse> locates = new ArrayList<>();

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "");

			String sql = "SELECT * FROM TB_ALEXA_PBO_LOCATE";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				LocateResponse locateResponse = new LocateResponse();
				locateResponse.setId(resultSet.getInt("ID"));
				locateResponse.setTicker(resultSet.getString("TICKER"));
				locateResponse.setBroker(resultSet.getString("BROKER"));
				locateResponse.setCompleted(resultSet.getLong("COMPLETED"));
				locateResponse.setPending(resultSet.getLong("PENDING"));
				locateResponse.setRejected(resultSet.getLong("REJECTED"));
				locates.add(locateResponse);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return locates;
	}

	@Override
	public String placeLocate(LocateRequest locateRquest) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String response = "";

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "");

			String sql = "INSERT INTO TB_ALEXA_PBO_LOCATE (TICKER, BROKER, COMPLETED, PENDING, REJECTED) "
					+ "VALUES (?, ?, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, locateRquest.getTicker());
			pstmt.setString(2, locateRquest.getBroker());
			pstmt.setLong(3, locateRquest.getQuantity());
			pstmt.setLong(4, 0);
			pstmt.setLong(5, 0);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				response = "Successfully placed locate request";
			} else {
				response = "Failed to place locate request";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@Override
	public TickerDetail getTickerDetail(String ticker, String attribute) {
		ResultSet resultSet = null;
		PreparedStatement pstmt = null;

		TickerDetail tickerDetail = new TickerDetail();
		tickerDetail.setTicker(ticker);
		tickerDetail.setAttibute(attribute);

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:dataSource", "sa", "")) {

			String sql = "SELECT " + attribute + " FROM TB_ALEXA_PBO_TIC_DET WHERE TICKER = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, ticker);

			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				tickerDetail.setValue(resultSet.getString(attribute));
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return tickerDetail;
	}

}
