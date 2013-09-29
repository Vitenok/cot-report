package com.iti.cftc.cot.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.stereotype.Component;

@Component("instrumentItemPreparedStatementSetter")
public class InstrumentItemPreparedStatementSetter implements ItemPreparedStatementSetter<InstrumentData> {
	@Override
	public void setValues(InstrumentData instrumentData, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setString(1, instrumentData.getName());
		preparedStatement.setString(2, instrumentData.getExchangeName());
		Date sqlDate = new Date(instrumentData.getDate().getTime());
		preparedStatement.setDate(3, sqlDate);
		preparedStatement.setLong(4, instrumentData.getProducersChange());
		preparedStatement.setLong(5, instrumentData.getSwapDealersChange());
		preparedStatement.setLong(6, instrumentData.getManagedMoneyChange());
		preparedStatement.setLong(7, instrumentData.getOtherReportableChange());
	}
}
