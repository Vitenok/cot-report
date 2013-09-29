package com.iti.cftc.cot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.iti.cftc.cot.model.InstrumentData;

@Service("nonFinancialDAO")
public class NonFinancialDAO implements ReportDAO<InstrumentData> {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@Cacheable("nonReportableInstrumentsData")
	public List<InstrumentData> getAllInstrumentData() {

		String sql = "select * from nonfinancial_instruments order by name, date";
		List<InstrumentData> instruments = jdbcTemplate.query(sql, new RowMapper<InstrumentData>() {

			@Override
			public InstrumentData mapRow(ResultSet rs, int rowNum) throws SQLException {

				InstrumentData iData = new InstrumentData();
				iData.setName(rs.getString("name"));
				iData.setExchangeName(rs.getString("exchange_name"));
				iData.setDate(rs.getDate("date"));
				iData.setProducersChange(rs.getLong("producers_change"));
				iData.setSwapDealersChange(rs.getLong("swap_dealers_change"));
				iData.setManagedMoneyChange(rs.getLong("managed_money_change"));
				iData.setOtherReportableChange(rs.getLong("other_reportable_change"));

				return iData;
			}
		});
		
		return instruments;
	}

}
