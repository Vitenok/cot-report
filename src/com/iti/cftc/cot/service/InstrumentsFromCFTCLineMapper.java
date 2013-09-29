package com.iti.cftc.cot.service;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

import com.iti.cftc.cot.model.InstrumentData;

@Component("instrumentsFromCFTCLineMapper")
public class InstrumentsFromCFTCLineMapper implements LineMapper<InstrumentData> {

	static Connection connection;

	private static final int INSTRUMENT_AND_EXCHANGE_NAME_IND = 1;
	private static final int AS_OF_IN_FORM_DATE_IND = 2;
	private static final int OTHER_REP_POSITIONS_SHORT_IND = 17;
	private static final int OTHER_REP_POSITIONS_LONG_IND = 16;
	private static final int M_MONEY_POSITIONS_SHORT_IND = 14;
	private static final int M_MONEY_POSITIONS_LONG_IND = 13;
	private static final int SWAP_POSITIONS_SHORT_IND = 11;
	private static final int SWAP_POSITIONS_LONG_IND = 10;
	private static final int PROD_MERC_POSITIONS_SHORT_IND = 9;
	private static final int PROD_MERC_POSITIONS_LONG_IND = 8;

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public InstrumentData mapLine(String line, int lineNumber) throws Exception {
		// typically name-exchange is 'WHEAT - KANSAS CITY BOARD OF TRADE'
		// but there is some exceptions like 'MILK, Class III - CHICAGO MERCANTILE EXCHANGE'
		// so it's not possible to use ',' delimeter
		String[] rawData = line.split("\"");
		return parseNonFinacialArray(rawData);
	}

	private static InstrumentData parseNonFinacialArray(String[] rawData) {

		InstrumentData data = new InstrumentData();

		String instrumentAndExchangesNames = rawData[INSTRUMENT_AND_EXCHANGE_NAME_IND];
		String delimeter = " - ";
		int delimeterIndex = instrumentAndExchangesNames.indexOf(delimeter);

		data.setName(instrumentAndExchangesNames.substring(0, delimeterIndex));
		data.setExchangeName(instrumentAndExchangesNames.substring(delimeterIndex + delimeter.length(), instrumentAndExchangesNames.length()));

		rawData = rawData[2].split(",");

		try {
			data.setDate(sdf.parse(rawData[AS_OF_IN_FORM_DATE_IND]));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// All deltas=long-short
		long deltaProdMercPositions = Long.parseLong(rawData[PROD_MERC_POSITIONS_LONG_IND].trim()) - Long.parseLong(rawData[PROD_MERC_POSITIONS_SHORT_IND].trim());
		data.setProducersChange(deltaProdMercPositions);

		long deltaSwapPositions = Long.parseLong(rawData[SWAP_POSITIONS_LONG_IND].trim()) - Long.parseLong(rawData[SWAP_POSITIONS_SHORT_IND].trim());
		data.setSwapDealersChange(deltaSwapPositions);

		long deltaMMoneyPositions = Long.parseLong(rawData[M_MONEY_POSITIONS_LONG_IND].trim()) - Long.parseLong(rawData[M_MONEY_POSITIONS_SHORT_IND].trim());
		data.setManagedMoneyChange(deltaMMoneyPositions);

		long deltaOtherReptPositions = Long.parseLong(rawData[OTHER_REP_POSITIONS_LONG_IND].trim()) - Long.parseLong(rawData[OTHER_REP_POSITIONS_SHORT_IND].trim());
		data.setOtherReportableChange(deltaOtherReptPositions);

		return data;
	}

}