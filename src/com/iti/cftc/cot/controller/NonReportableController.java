package com.iti.cftc.cot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iti.cftc.cot.dao.ReportDAO;
import com.iti.cftc.cot.model.InstrumentData;

@Controller
@RequestMapping("/client")
public class NonReportableController {

	@Autowired
	@Qualifier("nonFinancialDAO")
	ReportDAO<InstrumentData> nonFinancialDAO;

	@RequestMapping(method = RequestMethod.GET, value="/nonfin")
	public @ResponseBody List<InstrumentData> getNonFinData() {

		List<InstrumentData> data = nonFinancialDAO.getAllInstrumentData();

		return data;
	}
}
